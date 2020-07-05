package com.example.demo.logic;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.utils.Validator;

@Document(collection = "CUSTOMERS")
public class Customer {
	public static final String birthdateFormat = "dd-MM-yyyy";
	@Id
	private String email;
	private String firstName;
	private String lastName;
	private String birthdate; //yyyyMMdd
	private String countryCode;
	private String countryName;
	

	
	public Customer(String email, String firstName, String lastName, String birthdate, String countryCode,
			String countryName) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.countryCode = countryCode;
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
	
	@Transient
	public boolean isValid() {
		if(!Validator.isDateValid(birthdate, birthdateFormat)) {
			return false;
		}
		if(!Validator.isEmailValid(email)) {
			return false;
		}
		if(countryCode.length() != 2) {
			return false;
		}
		return true;
	}




}