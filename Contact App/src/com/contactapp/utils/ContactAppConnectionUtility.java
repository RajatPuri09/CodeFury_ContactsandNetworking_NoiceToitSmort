package com.contactapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ContactAppConnectionUtility {
		public static Connection getDerbyConnection() {
		
		Connection derbyConnection=null;
			
		
		String url = "jdbc:derby:D:\\Database\\CodeFuryDB;create=true";
        
        try {
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			derbyConnection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.err.println("CONNECTION FAILED.");
		}
		
		if (derbyConnection != null) {
	           System.err.println("CONNECTION ESTABLISHED.");
	    }
			
		return derbyConnection;
	}

}
