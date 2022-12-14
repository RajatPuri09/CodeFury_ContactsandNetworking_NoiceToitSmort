package com.contactapp.models;

import java.util.Objects;


public class SystemAdministrator 
{

	private int adminId;
	private String fullName;
	private String email;
	private long phoneNumber;
	private String username;
	private String password;
	
	/**
	 * 
	 */
	
	public SystemAdministrator()
	{
		super();
	}
	

	/**
	 * @param adminId
	 * @param fullName
	 * @param email
	 * @param phoneNumber
	 * @param username
	 * @param password
	 */
	public SystemAdministrator(int adminId, String fullName, String email, long phoneNumber, String username, String password) {
		this.adminId = adminId;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNumber
	 */
	public long getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
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
		return Objects.hash(email, fullName, phoneNumber, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SystemAdministrator other = (SystemAdministrator) obj;
		return Objects.equals(email, other.email) && Objects.equals(fullName, other.fullName)
				&& phoneNumber == other.phoneNumber && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Admin [fullName=" + fullName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", username="
				+ username + "]";
	}


};
