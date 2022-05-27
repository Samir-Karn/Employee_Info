package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;

	public class DatabaseConnection {
		public static Connection getDBConnection () throws Exception
		{
			Connection conn = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection
						("jdbc:mysql://testing.c1wgngcrsrdn.us-east-2.rds.amazonaws.com/employee","root","rootuser");
				return conn;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				throw e;
			}
		}
	}
