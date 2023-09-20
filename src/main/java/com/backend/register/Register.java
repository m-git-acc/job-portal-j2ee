package com.backend.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.extra.DBConnection;


@WebServlet("/reg")
public class Register extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String name1 = req.getParameter("name1");
		String email1 = req.getParameter("email1");
		String pass1 = req.getParameter("pass1");
		String gender1 = req.getParameter("gender1");
		String []field1 = req.getParameterValues("field1");
		String city1 = req.getParameter("city1");
		
		String fileds2 = "";
		for(String s:field1)
		{
			fileds2=s+", "+fileds2;
		}
		
		try(
				Connection c = DBConnection.getConnect();
				PreparedStatement ps = c.prepareStatement("INSERT INTO register(name,email,pass,gender,field,city) VALUES(?,?,?,?,?,?)");
			)
		{
			ps.setString(1, name1);
			ps.setString(2, email1);
			ps.setString(3, pass1);
			ps.setString(4, gender1);
			ps.setObject(5, fileds2);
			ps.setString(6, city1);
			
			int i1 = ps.executeUpdate();

			
			try(
					Connection c2 = DBConnection.getConnect();
					PreparedStatement ps2 = c2.prepareStatement("INSERT INTO about_user(email,about,skills) VALUES(?,?,?)");
				)
			{
				ps2.setString(1, email1);
				ps2.setString(2, "");
				ps2.setString(3, "");
				
				int i2 = ps2.executeUpdate();
				
				
				try(
						Connection c3 = DBConnection.getConnect();
						PreparedStatement ps3 = c3.prepareStatement("insert into profile_pics(email, path) values(?,?)");
					)
				{
		            ps3.setString(1, email1);
		            ps3.setString(2, "profilepic.png");
		            ps3.execute();
					
					if(i1>0 && i2>0)
					{
						HttpSession session = req.getSession();
						session.setAttribute("session_name", name1);
						session.setAttribute("session_email", email1);
						session.setAttribute("session_gender", gender1);
						session.setAttribute("session_city", city1);
						session.setAttribute("session_fields", fileds2);
	
						session.setAttribute("session_title", "");
						session.setAttribute("session_skills", "");
		                
		                session.setAttribute("session_profilepic", "profilepic.png");
						
						res.sendRedirect("index.jsp");
					}
					else 
					{
						out.print("Failed");
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
