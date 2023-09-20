package com.backend.profile;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.extra.DBConnection;


@WebServlet("/EditProfileAbout")
public class EditProfileAbout extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		PrintWriter out = res.getWriter();
		 
		HttpSession session = req.getSession();
		String email2 = (String) session.getAttribute("session_email");
		String name2 = req.getParameter("name1");
		String city2 = req.getParameter("city1");
		String gender2 = req.getParameter("gender1");
		String title2 = req.getParameter("title1");
		String skills2 = req.getParameter("skills1");
		

		try(
				Connection c1 = DBConnection.getConnect();
				PreparedStatement ps1 = c1.prepareStatement("UPDATE register SET name=?,city=?,gender=? WHERE email=?");
			)
		{
			ps1.setString(1, name2);
			ps1.setString(2, city2);
			ps1.setString(3, gender2);
			ps1.setString(4, email2);
			int i1 = ps1.executeUpdate();
			

			try(
					Connection c2 = DBConnection.getConnect();
					PreparedStatement ps2 = c2.prepareStatement("UPDATE about_user SET about=?,skills=? WHERE email=?");
				)
			{
				ps2.setString(1, title2);
				ps2.setString(2, skills2);
				ps2.setString(3, email2);
				int i2 = ps2.executeUpdate();
				
				if(i1>0 && i2>0)
				{
					session.setAttribute("session_name", name2);
					session.setAttribute("session_email", email2);
					session.setAttribute("session_gender", gender2);
					session.setAttribute("session_city", city2);

					session.setAttribute("session_title", title2);
					session.setAttribute("session_skills", skills2);
					
					res.sendRedirect("profile.jsp");
				}
				else 
				{
					RequestDispatcher rd1 = req.getRequestDispatcher("error.jsp");
					rd1.include(req, res);
					
					RequestDispatcher rd2 = req.getRequestDispatcher("edit-profile-about.jsp");
					rd2.include(req, res);
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
