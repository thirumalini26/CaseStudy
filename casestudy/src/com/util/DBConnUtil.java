package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {

	
	public static Connection connection = null;

	public DBConnUtil() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public static Connection getConnection()
	{
		//singleton design pattern
		if (connection == null)
		{
			try
			{
				
				
				connection = DriverManager.getConnection(
													DBPropertyUtil.get("db.url"),
													DBPropertyUtil.get("db.username"),
													DBPropertyUtil.get("db.password")
						);
				
			}
		catch(Exception  e)
		{
			e.printStackTrace();
		}
		}
		return connection;
	}
	
}
