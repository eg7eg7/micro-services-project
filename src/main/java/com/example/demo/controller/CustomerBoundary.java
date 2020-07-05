package com.example.demo.controller;

import com.example.demo.exception.BadRequestException;
import com.example.demo.logic.Country;
import com.example.demo.logic.Customer;
import com.example.demo.utils.Validator;

public class CustomerBoundary {
	private String email;
	private Name name;
	private String birthdate; //DD-MM-YYYY
	private Country country;
	
	public CustomerBoundary() {
		
	}
	
	public CustomerBoundary(String email, Name name, String birthdate, Country country) {
		super();
		this.email = email;
		this.name = name;
		this.birthdate = birthdate;
		this.country = country;
	}
	
	public CustomerBoundary(Customer c) {
		super();
		email = c.getEmail();
		name = new Name(c.getFirstName(), c.getLastName());
		birthdate = c.getBirthdate();
		country = new Country(c.getCountryCode(), c.getCountryName());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	public Customer toEntity() {
		return new Customer(email, name.getFirst(), name.getLast(), birthdate, country.getCountryCode(), country.getCountryName());
	}
	
}
