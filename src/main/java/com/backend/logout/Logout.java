package com.backend.logout;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logout extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		try
		{
	        Cookie[] ck=req.getCookies();
	        for(Cookie cookie : ck)
	        {
	            cookie.setMaxAge(0);
	            res.addCookie(cookie);
	        }
	        
	        HttpSession session=req.getSession();
	        session.invalidate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
        
        res.sendRedirect("login.jsp");
	}

}
