package com.contactapp.models;

import java.util.Date;

public class ContactPerson extends Human
{
	public ContactPerson() {
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
	public ContactPerson(String fullName, String email, long phoneNumber, String gender, Date dateOfBirth,
			String address, String city, String state, String country, String company, byte[] image) {
		super(fullName, email, phoneNumber, gender, dateOfBirth, address, city, state, country, company, image);
	}

	/**
	 * @param fullName
	 * @param email
	 */
	public ContactPerson(String fullName, String email) {
		super(fullName, email);
	}

};
