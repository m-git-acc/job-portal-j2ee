package com.backend.education;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.extra.DBConnection;

@WebServlet("/AddEducationDetails")
public class AddEducationDetails extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        PrintWriter out=resp.getWriter();
        
        HttpSession session=req.getSession();
        String email2=(String)session.getAttribute("session_email");
        
        String school2=req.getParameter("school1");
        String degree2=req.getParameter("degree1");
        String year2=req.getParameter("year1");
        String grade2=req.getParameter("grade1");
        String description2=req.getParameter("description1");
        
        try(
				Connection c = DBConnection.getConnect();
				PreparedStatement ps = c.prepareStatement("insert into education(email, school, degree, year, grade, description) values(?,?,?,?,?,?)");
			)
        {
            ps.setString(1, email2);
            ps.setString(2, school2);
            ps.setString(3, degree2);
            ps.setString(4, year2);
            ps.setString(5, grade2);
            ps.setString(6, description2);
            int i=ps.executeUpdate();
            
            if(i>0)
            {
                resp.sendRedirect("profile.jsp");
            }
            else
            {
                RequestDispatcher rd1=req.getRequestDispatcher("error.jsp");
                rd1.include(req, resp);
                
                RequestDispatcher rd2=req.getRequestDispatcher("edit-profile-education.jsp");
                rd2.include(req, resp);
            }
        }
        catch(Exception e)
        {
            out.println(e);
        }
    }
}
