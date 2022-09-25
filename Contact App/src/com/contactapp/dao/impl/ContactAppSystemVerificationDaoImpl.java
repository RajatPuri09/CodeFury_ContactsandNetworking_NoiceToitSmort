package com.contactapp.dao.impl;






import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.contactapp.dao.ContactAppSystemVerificationDao;
import com.contactapp.models.SystemAdministrator;
import com.contactapp.models.SystemUser;
import com.contactapp.utils.ContactAppConnectionUtility;
import com.contactapp.utils.ContactAppSystemAdminParser;

public class ContactAppSystemVerificationDaoImpl implements ContactAppSystemVerificationDao 
{
	Connection derbyConnection = null;

	/**
	 * 
	 */
	public ContactAppSystemVerificationDaoImpl() {
		super();
		this.derbyConnection = ContactAppConnectionUtility.getDerbyConnection();
	}

	public boolean verifyAdmin(String userName, String password) {
		ContactAppSystemAdminParser parser=new ContactAppSystemAdminParser();
		boolean verified = false;
			try {
				List<SystemAdministrator> adminList=parser.adminDetails();
				 for(SystemAdministrator item:adminList)
				    {	
				    	if(item.getUsername().equals(userName) && item.getPassword().equals(password))
				    	{
				    		verified = true;
				    	}
				    	else
				    	{
				    		verified = false;
				    	}
				    }
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				System.err.println("Exception occurred while parsing xml file." + e.getMessage());
			}
			return verified;

	}

	public int verifyUser(String userName, String password) throws Exception {
		String sql1 = "select * from users where username=?";
		PreparedStatement pstmt = null;

		
		pstmt = this.derbyConnection.prepareStatement(sql1);
		pstmt.setString(1, userName);
		
		ResultSet result = pstmt.executeQuery();
		
		String passFromDb;
		int userId;
		
		if (result.next()) {
			passFromDb = result.getString("password");
		} else {
			throw new Exception("User does not exists");
		}
		
		if (passFromDb.equals(password)) {

			userId = result.getInt("id");
		} else {

			throw new Exception("Wrong password.");
		}
		return userId;

	}

	public boolean registerUserToTheSystem(SystemUser user) {
		String sql = "insert into users(fullName, email, phoneNumber, gender, dateOfBirth, address, city, state, country, company, image, username, password) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement pstmt = null;

		InputStream fin=null;
		try {
			fin = new FileInputStream("C");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int rowUpdated = 0;
		
		try {
			pstmt = this.derbyConnection.prepareStatement(sql);
			
			pstmt.setString(1, user.getFullName());
			pstmt.setString(2, user.getEmailId());
			pstmt.setLong(3, user.getPhnNumber());
			pstmt.setString(4, user.getGender());
//			System.out.println(user.getDateOfBirth());
			pstmt.setDate(5, new java.sql.Date(user.getDateOfBirth().getTime()));
			pstmt.setString(6, user.getAddress());
			pstmt.setString(7, user.getCity());
			pstmt.setString(8, user.getState());
			pstmt.setString(9, user.getCountry());
			pstmt.setString(10, user.getCompany());
			pstmt.setBlob(11,fin);

			pstmt.setString(12, user.getUsername());
			pstmt.setString(13, user.getPassword());
			
			rowUpdated = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		
		return rowUpdated==1 ? true:false;
	}

	}


