package com.example.demo.models;

import java.text.ParseException;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.utils.Util;

@Document(collection = "CUSTOMERS")
public class Customer {
	public static final String birthdateFormat = "dd-MM-yyyy";
	@Id
	private String email;
	private String firstName;
	private String lastName;
	private String birthdate; // ddMMyyyy
	private String countryCode;
	private String countryName;

	public Customer(String email, String firstName, String lastName, String birthdate, String countryCode,
			String countryName) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.countryCode = countryCode.toUpperCase();
		this.countryName = countryName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public Date getBirthdayDate() {
		try {
			return Util.getDate(birthdate, birthdateFormat);
		} catch (ParseException e) {
			return new Date();
		}
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public boolean isValid() {
		if (!Util.isDateValid(birthdate, birthdateFormat)) {
			System.err.println("Date invalid " + birthdate);
			return false;
		}
		if (!Util.isEmailValid(email)) {
			System.err.println("Email invalid " + email);
			return false;
		}
		if (countryCode.length() != 2) {
			System.err.println("Country invalid " + countryCode);
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Customer [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", birthdate="
				+ birthdate + ", countryCode=" + countryCode + ", countryName=" + countryName + "]";
	}

}
