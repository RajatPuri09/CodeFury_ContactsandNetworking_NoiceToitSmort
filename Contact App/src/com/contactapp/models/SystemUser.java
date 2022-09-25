package com.contactapp.models;

import java.util.Date;

public class SystemUser extends Human
{
	private int id;
	private String username;
	private String password;
	
	/**
	 * 
	 */
	//no- argument constructor
	public SystemUser() {
		super();
		
	}




	/**
	 * @param fullName
	 * @param email
	 * @param phoneNumber
	 * @param gender
	 * @param dateOfBirth
	 * @param address
	 * @param city
	 * @param state
	 * @param country
	 * @param company
	 * @param image
	 */
	public SystemUser(String fullName, String email, long phoneNumber, String gender, Date dateOfBirth, String address,
			String city, String state, String country, String company, byte[] image) {
		
		super(fullName, email, phoneNumber, gender, dateOfBirth, address, city, state, country, company, image);
		
	}




	/**
	 * @param city
	 * @param state
	 * @param country
	 */
	public SystemUser(String city, String state, String country) {
		super(city, state, country);
		// TODO Auto-generated constructor stub
	}




	/**
	 * @param fullName
	 * @param email
	 */
	public SystemUser(String fullName, String email) {
		super(fullName, email);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param id
	 * @param username
	 * @param password
	 */
	public SystemUser(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * @param fullName
	 * @param email
	 * @param phoneNumber
	 * @param gender
	 * @param dateOfBirth
	 * @param address
	 * @param city
	 * @param state
	 * @param country
	 * @param company
	 * @param image
	 * @param id
	 * @param username
	 */
	public SystemUser(String fullName, String email, long phoneNumber, String gender, Date dateOfBirth, String address,
			String city, String state, String country, String company, byte[] image, int id, String username) {
		super(fullName, email, phoneNumber, gender, dateOfBirth, address, city, state, country, company, image);
		this.id = id;
		this.username = username;
	}


	/**
	 * @param city
	 * @param state
	 * @param country
	 * @param id
	 */
	public SystemUser(String city, String state, String country, int id) {
		super(city, state, country);
		this.id = id;
	}




	public SystemUser(String fullName, String email, long phoneNumber, String gender, Date dateOfBirth, String address,
			String city, String state, String country, String company, byte[] image, String userName2,
			String  password2) {
		super(fullName, email, phoneNumber, gender, dateOfBirth, address, city, state, country, company, image);
		this.username = userName2;
		this.password = password2;
		
	}




	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}




	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}




	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(username);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SystemUser other = (SystemUser) obj;
		return Objects.equals(username, other.username);
	}




	@Override
	public String toString() {
//		return "User [username=" + username + ", password=" + password + "]";
		return "User id = "+ id +" city=" + this.getCity() + ", State="
		+ this.getState() + ", country=" + this.getCountry();
	}
	
	public String toStringIdLoc() {
		return "User id = "+ id +" city=" + this.getCity() + ", State="
				+ this.getState() + ", country=" + this.getCountry();
	}

};
