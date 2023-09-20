package com.backend.register;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


@WebFilter("/reg")
public class ValidationFilter implements Filter
{

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException 
	{
		
		String name1 = req.getParameter("name1");
		String email1 = req.getParameter("email1");
		String pass1 = req.getParameter("pass1");
		String gender1 = req.getParameter("gender1");
		String city1 = req.getParameter("city1");
        
        ValidationServerSide validations=new ValidationServerSide();
        
        if(!validations.nameValidate(name1))
        {
            RequestDispatcher rd1=req.getRequestDispatcher("register.jsp");
            rd1.include(req, res);
        }
        else if(!validations.emailValidate(email1))
        {
            RequestDispatcher rd1=req.getRequestDispatcher("register.jsp");
            rd1.include(req, res);
        }
        else if(!validations.passwordValidate(pass1))
        {
            RequestDispatcher rd1=req.getRequestDispatcher("register.jsp");
            rd1.include(req, res);
        }
        else if(gender1==null || gender1.equals(""))
        {
            RequestDispatcher rd1=req.getRequestDispatcher("register.jsp");
            rd1.include(req, res);
        }
        else if(city1==null || city1.equals(""))
        {
            RequestDispatcher rd1=req.getRequestDispatcher("register.jsp");
            rd1.include(req, res);
        }
        else
        {
            chain.doFilter(req, res);
        }
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

}
