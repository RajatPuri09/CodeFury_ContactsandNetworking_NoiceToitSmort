package com.contactapp.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import com.contactapp.dao.ContactAppSystemUserDao;
import com.contactapp.models.ContactPerson;
import com.contactapp.models.SystemUser;
import com.contactapp.utils.ContactAppConnectionUtility;
import com.contactapp.utils.ContactAppNameComparator;
import com.contactapp.utils.NameComparator;

public class ContactAppSystemUserDaoImpl implements ContactAppSystemUserDao
{
	Connection derbyConnection = null;
	
	
	/**
	 * 
	 */
	public ContactAppSystemUserDaoImpl() {
		super();
		
		this.derbyConnection = ContactAppConnectionUtility.getDerbyConnection();
	}


	public boolean addContactToTheList(int id, ContactPerson contact) {
		String sql = "insert into contacts(userid,fullName,email,phoneNumber,gender,dateOfBirth,address,"
			+ "city,state,country,company,image) "
			+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";

	PreparedStatement pstmt = null;
	// InputStream imageInputStream = new ByteArrayInputStream(contact.getImage());
	InputStream fin=null;
	try {
		fin = new FileInputStream("");
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	int rowUpdated = 0;

	try {
		pstmt = this.derbyConnection.prepareStatement(sql);
		
		pstmt.setInt(1, id);
		pstmt.setString(2, contact.getFullName());
		pstmt.setString(3, contact.getEmailId());
		pstmt.setLong(4, contact.getPhnNumber());
		pstmt.setString(5, contact.getGender());
		
		
		pstmt.setDate(6, new java.sql.Date(contact.getDateOfBirth().getTime()));
		pstmt.setString(7, contact.getAddress());
		pstmt.setString(8, contact.getCity());
		pstmt.setString(9, contact.getState());
		pstmt.setString(10, contact.getCountry());
		pstmt.setString(11, contact.getCompany());
		pstmt.setBlob(12,fin);
		
		rowUpdated = pstmt.executeUpdate();

	} catch (SQLException e) {
		
		e.printStackTrace();
	}

	return rowUpdated==1 ? true:false;

}


	public boolean editContact(int id, ContactPerson contact) {
	

			
			String sql = "update contacts set phoneNumber=?,gender=?,dateOfBirth=?,address=?,city=?,state=?,country=?,company=?,image=? where email = ?";
			
			PreparedStatement pstmt = null;
			InputStream imageInputStream = new ByteArrayInputStream(contact.getProfileImage());
			int rowUpdated = 0;
			
			try {
				pstmt = this.derbyConnection.prepareStatement(sql);
				
				pstmt.setLong(1, contact.getPhnNumber());
				pstmt.setString(2, contact.getGender());
				pstmt.setDate(3, new java.sql.Date(contact.getDateOfBirth().getTime()));
				pstmt.setString(4, contact.getAddress());
				pstmt.setString(5, contact.getCity());
				pstmt.setString(6, contact.getState());
				pstmt.setString(7, contact.getCountry());
				pstmt.setString(8, contact.getCompany());
				pstmt.setBlob(9,imageInputStream);
				pstmt.setString(10, contact.getEmailId());
				
				rowUpdated = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.err.println("Contact could not be updated. " + e.getMessage());
			}
			
			return rowUpdated==1 ? true:false;
	}


	public boolean addFriendToTheList(int userId1, int userId2) {
		String sql = "update relationship set status=?, actionId=? where userId1=? and userId2=?";

		PreparedStatement pstmt = null;
		int rowUpdated = 0;

		try {
			pstmt = this.derbyConnection.prepareStatement(sql);
	
			//pstmt.setInt(1,0);
			
			pstmt.setInt(1, 1);
			pstmt.setInt(2, userId2);
			pstmt.setInt(3, userId1);
			pstmt.setInt(4, userId2);
			
			
			rowUpdated = pstmt.executeUpdate();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	
		}

		return rowUpdated==1 ? true:false;

	}


	public Collection<ContactPerson> viewContactsList(int id) {
		TreeSet<ContactPerson> contacts=new TreeSet<ContactPerson>(new ContactAppNameComparator());
		String sql = "select * from contacts where userid=?";
		
		PreparedStatement pstmt = null;
		
		try {
			
			pstmt = this.derbyConnection.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet result = pstmt.executeQuery();
			
			
			while(result.next()) {
			
//				int id=result.getInt("userid");
				String fullName = result.getString("fullName");
				String email=result.getString("email");
				long phoneNumber=result.getLong("phoneNumber");
				String gender=result.getString("gender");
				Date dateOfBirth=result.getDate("dateOfBirth");
				String address=result.getString("address");
				String city=result.getString("city");
				String state=result.getString("state");
				String country=result.getString("country");
				String company=result.getString("company");
				Blob image=result.getBlob("image");
				
				
				byte barr[]=image.getBytes(1,(int)image.length());//1 means first image  
	              
				//FileOutputStream fout=new FileOutputStream("D:\\HSBC\\CodeFury\\CodeFury_Contacts-Networking_Application\\Contacts_Networking_Application\\sonoo.jpg");  
				//fout.write(barr);  
				              
				//fout.close();  
				
				ContactPerson contact=new ContactPerson(fullName, email, phoneNumber, gender, dateOfBirth, address, city, state, country, company, barr);
				
			
				System.err.println(contacts.add(contact));
			}
			
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return contacts;

	}


	public Collection<SystemUser> viewFriendsList(int id) {
		String sql = "SELECT users.id,users.username,"
			+ " from users left outer join relationship "
			+ "on (users.id=relationship.userid1 or users.id=relationship.userid2) "
			+ "where relationship.status=? and users.id=?";
	Set<SystemUser> sortedFriends=new TreeSet<SystemUser>();
	PreparedStatement pstmt = null;
	
	try {
		
		pstmt = this.derbyConnection.prepareStatement(sql);
		pstmt.setInt(1, 1);
		pstmt.setInt(2, id);
		ResultSet result = pstmt.executeQuery();
		
		
		while(result.next()) {
		
			int userId=result.getInt("id");
			String fullName = result.getString("fullName");
			String email=result.getString("email");
			long phoneNumber=result.getLong("phoneNumber");
			String gender=result.getString("gender");
			Date dateOfBirth=result.getDate("dateOfBirth");
			String address=result.getString("address");
			String city=result.getString("city");
			String state=result.getString("state");
			String country=result.getString("country");
			String company=result.getString("company");
			Blob image=result.getBlob("image");
			String username=result.getString("username");
			
			
			byte barr[]=image.getBytes(1,(int)image.length());//1 means first image  
	          
			//FileOutputStream fout=new FileOutputStream("D:\\HSBC\\CodeFury\\CodeFury_Contacts-Networking_Application\\Contacts_Networking_Application\\sonoo.jpg");  
			//fout.write(barr);  
			              
			//fout.close();  
			
			SystemUser user=new SystemUser(fullName, email, phoneNumber, gender, dateOfBirth, 
								address, city, state, country, company, barr, userId, username);
			
			
			sortedFriends.add(user);
		}
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return sortedFriends;

	}


	public Collection<SystemUser> viewBlockedUsersList(int userId) {
		String sql = " select * from users where username in( SELECT users.username from users inner join "
			+ "relationship on (users.id=relationship.userid1) where userid2=? and relationship.status=3 union  "
			+ "SELECT users.username from users inner join relationship on (users.id=relationship.userid2) "
			+ "where userid1=? and relationship.status=3 )";
	Set<SystemUser> sortedFriends=new TreeSet<SystemUser>(new ContactAppNameComparator());
	PreparedStatement pstmt = null;
	
	try {
		
		pstmt = this.derbyConnection.prepareStatement(sql);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, userId);
		ResultSet result = pstmt.executeQuery();
		
		
		
		
		while(result.next()) {
		
			int id=result.getInt("id");
			String fullName = result.getString("fullName");
			String email=result.getString("email");
			long phoneNumber=result.getLong("phoneNumber");
			String gender=result.getString("gender");
			Date dateOfBirth=result.getDate("dateOfBirth");
			String address=result.getString("address");
			String city=result.getString("city");
			String state=result.getString("state");
			String country=result.getString("country");
			String company=result.getString("company");
			Blob image=result.getBlob("image");
			String username=result.getString("username");
			
			
			byte barr[]=image.getBytes(1,(int)image.length());//1 means first image  
              
			 
			
			SystemUser user=new SystemUser(fullName, email, phoneNumber, gender, dateOfBirth, 
								address, city, state, country, company, barr, id, username);
			
			
			sortedFriends.add(user);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return sortedFriends;

		
	}


	public SystemUser searchUser(String nameEmail) {
		String sql = "select * from users where username=? or email=?";
		
		PreparedStatement pstmt = null;
		
		SystemUser user = null;
		
			try {
				pstmt = this.derbyConnection.prepareStatement(sql);
				
				pstmt.setString(1, nameEmail);
				pstmt.setString(2, nameEmail);
				ResultSet result = pstmt.executeQuery();
				
				if(result.next()) {
					
					int id= result.getInt("id");
					String fullName = result.getString("fullName");
					String email=result.getString("email");
					long phoneNumber=result.getLong("phoneNumber");
					String gender=result.getString("gender");
					Date dateOfBirth=result.getDate("dateOfBirth");
					String address=result.getString("address");
					String city=result.getString("city");
					String state=result.getString("state");
					String country=result.getString("country");
					String company=result.getString("company");
					Blob image=result.getBlob("image");
					String username=result.getString("username");
					
					byte imageAsByteArray[]=image.getBytes(1,(int)image.length());//1 means first image
					
					user=new SystemUser(fullName, email, phoneNumber, gender, dateOfBirth, address, city, state, country, company, imageAsByteArray, id, username);
				} else {
					throw new Exception("User not found.");
				}
				
			} catch (SQLException e) {
				
				System.err.println(e.getMessage());
			} catch (Exception e) {
				
				System.err.println(e.getMessage());
			}
			
			return user;

	}


	public boolean blockUser(int userId1, int userId2) {
		String sql = "update relationship set status=?, actionId=? where userId1=? and userId2=?";

		PreparedStatement pstmt = null;
		int rowUpdated = 0;

		try {
			pstmt = this.derbyConnection.prepareStatement(sql);
	
			//pstmt.setInt(1,0);
			
			pstmt.setInt(1, 3);
			pstmt.setInt(2, userId2);
			pstmt.setInt(3, userId1);
			pstmt.setInt(4, userId2);
			
			
			rowUpdated = pstmt.executeUpdate();
	
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return rowUpdated==1 ? true:false;

	}


	public boolean unblockUser(int userId1, int userId2) {
		String sql = "delete from relationship where actionId=? and status=? and (userid1=? or userid2=?)";
		
		java.sql.PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			pstmt = this.derbyConnection.prepareStatement(sql);
			
			pstmt.setInt(1, userId1);
			pstmt.setInt(2, 3);
			pstmt.setInt(3, userId2);
			pstmt.setInt(4, userId2);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result==1 ? true:false; 

	}


	public boolean sendRequestToFriend(int userId1, int userId2) {
	String sql = "insert into relationship "
			+ "values(?,?,?,?)";

	PreparedStatement pstmt = null;
	

	int rowUpdated = 0;

	try {
		pstmt = this.derbyConnection.prepareStatement(sql);

		
		
		pstmt.setInt(1, userId1);
		pstmt.setInt(2, userId2);
		pstmt.setInt(3, 0);
		pstmt.setInt(4, userId1);
		rowUpdated = pstmt.executeUpdate();

	} catch (SQLException e) {
		
		e.printStackTrace();
	}

	return rowUpdated==1 ? true:false;

	}


	public boolean declineRequest(int userId1, int userId2) {
		String sql = "update relationship set status=?, actionId=? where userId1=? and userId2=?";

		PreparedStatement pstmt = null;
		int rowUpdated = 0;

		try {
			pstmt = this.derbyConnection.prepareStatement(sql);
	
		
			
			pstmt.setInt(1, 2);
			pstmt.setInt(2, userId2);
			pstmt.setInt(3, userId1);
			pstmt.setInt(4, userId2);
			
			
			rowUpdated = pstmt.executeUpdate();
	
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return rowUpdated==1 ? true:false;

	}


	public Set<SystemUser> viewFriendRequests(int userId) {
		String sql = " select * from users where username in( SELECT users.username from users inner join "
			+ "relationship on (users.id=relationship.userid1) where userid2=? and actionid!=? and relationship.status=0 union  "
			+ "SELECT users.username from users inner join relationship on (users.id=relationship.userid2) "
			+ "where userid1=? and actionid!=? and relationship.status=0 )";
	Set<SystemUser> sortedFriends=new TreeSet<SystemUser>(new ContactAppNameComparator());
	PreparedStatement pstmt = null;
	
	try {
		
		pstmt = this.derbyConnection.prepareStatement(sql);
		pstmt.setInt(1, (int)userId);
		pstmt.setInt(2, (int)userId);
		pstmt.setInt(3, (int)userId);
		pstmt.setInt(4, (int)userId);
		ResultSet result = pstmt.executeQuery();
		
		
		
		
		while(result.next()) {
		
			int id=(int)result.getInt("id");
			String fullName = result.getString("fullName");
			String email=result.getString("email");
			long phoneNumber=result.getLong("phoneNumber");
			String gender=result.getString("gender");
			Date dateOfBirth=result.getDate("dateOfBirth");
			String address=result.getString("address");
			String city=result.getString("city");
			String state=result.getString("state");
			String country=result.getString("country");
			String company=result.getString("company");
			Blob image=result.getBlob("image");
			String username=result.getString("username");
			
			
			byte barr[]=image.getBytes(1,(int)image.length());//1 means first image  
              
			  
			
			SystemUser user=new SystemUser(fullName, email, phoneNumber, gender, dateOfBirth, 
								address, city, state, country, company, barr, id,username);
			
			
			sortedFriends.add(user);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return sortedFriends;

	}
	

}
