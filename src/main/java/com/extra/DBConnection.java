package com.extra;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DBConnection 
{
	private static Connection c;
	
	public static Connection getConnect()
	{
		String jdbc_url=null, username = null, password = null;
		try
		{
			InputStream is = DBConnection.class.getResourceAsStream("db.properties");
			Properties p = new Properties();
			p.load(is);

			jdbc_url = p.getProperty("jdbc-url");
			username = p.getProperty("username");
			password = p.getProperty("password");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try
		{
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			c = DriverManager.getConnection("jdbc:mysql:///job_portal_j2ee","root","mohit123");			 
			
			
			// connection cooling
			MysqlDataSource ds = new MysqlDataSource();
			ds.setUrl(jdbc_url);
			ds.setUser(username);
			ds.setPassword(password);
			c = ds.getConnection();
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return c;
	}

}
