package com.contactapp.dao.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.contactapp.dao.ContactAppSystemAdminDao;
import com.contactapp.models.SystemUser;
import com.contactapp.utils.ContactAppConnectionUtility;

public class ContactAppSystemAdminDaoImpl implements ContactAppSystemAdminDao
{
	List<SystemUser> userList;
	Connection derbyConnection;
	
	/**
	 * 
	 */
	public ContactAppSystemAdminDaoImpl() {
		super();
		this.derbyConnection = ContactAppConnectionUtility.getDerbyConnection();
		this.userList = new ArrayList<SystemUser>();
	}

	public boolean loginSystemAdmin(String adminUsername, String adminPassword) {
		return false;
	}

	public boolean disableSystemUser(int id) {
		String sql = "update users set disabled=? where Id=?";

		PreparedStatement pstmt = null;
		int rowUpdated = 0;

		try {
			pstmt = this.derbyConnection.prepareStatement(sql);
	
			//pstmt.setInt(1,0);
			
			pstmt.setInt(1, 1);
			pstmt.setInt(2, (int)id);
			
			
			
			rowUpdated = pstmt.executeUpdate();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return rowUpdated==1 ? true:false;
	}

	

	public boolean deleteSystemUser(int id) {
		String sql = "delete from users where id = ?";
		
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			pstmt = this.derbyConnection.prepareStatement(sql);
			
			pstmt.setInt(1, (int)id);
						
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return result == 1 ? true : false;

	}

	public Collection<SystemUser> showSummary() {
		String sql = "select id,city,state,country from users";
		List<SystemUser> tempUserList = new ArrayList<SystemUser>();
		PreparedStatement pstmt = null;
		
		try {
			
			pstmt = this.derbyConnection.prepareStatement(sql);
			
			ResultSet result = pstmt.executeQuery();
			
			ResultSetMetaData metaData = result.getMetaData();
			
			int columnCount = metaData.getColumnCount();
			
			for(int i = 1; i<=columnCount; i++) {
				System.out.println("========= Columm:="+metaData.getColumnName(i));
			}
			
			DatabaseMetaData dbInfo = this.derbyConnection.getMetaData();
			
			System.out.println("Drvier Name:="+dbInfo.getDriverName());
			System.out.println("Product Version:="+dbInfo.getDatabaseProductVersion());
			
			while(result.next()) {
			
				int id=(int)result.getInt("id");
				String city=result.getString("city");
				String state=result.getString("state");
				String country=result.getString("country");
				
				
				
				SystemUser user=new SystemUser(city, state, country, id);
				
				
				tempUserList.add(user);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempUserList;

	}

	public Collection<SystemUser> viewUsersToDisable() {
		String sql = "select users.id,users.city,users.state,users.country from users where id in"
			+ "(select blockedusers.userid from "
			+ "(select userid1 as userid from relationship where status=3 and "
			+ "userid1!=actionid union all select userid2 as userid from relationship where "
			+ "status=3 and userid2!=actionid) as blockedusers group by userid having count(blockedusers.userid)>3)";
	
	this.userList.clear();
	PreparedStatement pstmt = null;
	
	
	try {
		
		pstmt = this.derbyConnection.prepareStatement(sql);
		
		ResultSet result = pstmt.executeQuery();
		
		ResultSetMetaData metaData = result.getMetaData();
		
		int columnCount = metaData.getColumnCount();
		
		for(int i = 1; i<=columnCount; i++) {
			System.out.println("========= Columm:="+metaData.getColumnName(i));
		}
		
		DatabaseMetaData dbInfo = this.derbyConnection.getMetaData();
		
		System.out.println("Drvier Name:="+dbInfo.getDriverName());
		System.out.println("Product Version:="+dbInfo.getDatabaseProductVersion());
		
		while(result.next()) {
		
			
			int id=(int)result.getInt("id");
			String city=result.getString("city");
			String state=result.getString("state");
			String country=result.getString("country");
			
			
			
			
			SystemUser user=new SystemUser(city, state, country,id);
			
			
			this.userList.add(user);
		}
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return this.userList;

		
	}

	public Collection<SystemUser> viewUsersToDelete() {
		return null;
	}

	

};
