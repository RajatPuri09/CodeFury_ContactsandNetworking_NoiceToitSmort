package com.contactapp.models;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class Human 
{
	//instance variables
	private String fullName;
	private String emailId;
	private long phnNumber;
	private String gender;
	private Date dateOfBirth;
	private String address;
	private String city;
	private String state;
	private String country;
	private String company;
	private byte[] profileImage;
	
	//no-argument Constructor
	public Human() {
		
	}
		
		
	
	
	/**
	 * @param fullName
	 * @param email
	 */
	public Human(String fullName, String emailId) {
		this.fullName = fullName;
		this.emailId = emailId;
	}
	
	

	/**
	 * @param city
	 * @param state
	 * @param country
	 */
	public Human(String city, String state, String country) {
		super();
		this.city = city;
		this.state = state;
		this.country = country;
	}

	/**
	 * @param fullName
	 * @param emailId
	 * @param phnNumber
	 * @param gender
	 * @param dateOfBirth
	 * @param address
	 * @param city
	 * @param state
	 * @param country
	 * @param company
	 * @param profileImage
	 */
	public Human(String fullName, String emailId, long phnNumber, String gender, Date dateOfBirth, String address,
			String city, String state, String country, String company, byte[] profileImage) {
		super();
		this.fullName = fullName;
		this.emailId = emailId;
		this.phnNumber = phnNumber;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.company = company;
		this.profileImage = profileImage;
	}
	
	//getter and setter methods
	public String getFullName() {
		return this.fullName;
	}
	
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getEmailId() {
		return this.emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public long getPhnNumber() {
		return this.phnNumber;
	}
	public void setPhnNumber(long phnNumber) {
		this.phnNumber = phnNumber;
	}
	public String getGender() {
		return gender;
	}
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return this.address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return this.city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return this.state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public byte[] getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(profileImage);
		result = prime * result + Objects.hash(address, state, city, company, country, dateOfBirth, emailId, fullName,
				gender, phnNumber);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Human other = (Human) obj;
		return Objects.equals(address, other.address) && Objects.equals(state, other.state)
				&& Objects.equals(city, other.city) && Objects.equals(company, other.company)
				&& Objects.equals(country, other.country) && Objects.equals(dateOfBirth, other.dateOfBirth)
				&& Objects.equals(emailId, other.emailId) && Objects.equals(fullName, other.fullName)
				&& Objects.equals(gender, other.gender) && Arrays.equals(profileImage, other.profileImage)
				&& phnNumber == other.phnNumber;
	}




	@Override
	public String toString() {
		return "SystemAdministrator [fullName=" + fullName + ", emailId="
				+ emailId + ", phnNumber=" + phnNumber + ", gender=" + gender
				+ ", dateOfBirth=" + dateOfBirth + ", address=" + address
				+ ", city=" + city + ", state=" + state + ", country="
				+ country + ", company=" + company;
	}
	

};
