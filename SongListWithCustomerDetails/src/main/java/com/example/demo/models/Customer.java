package com.example.demo.models;

public class Customer {
	private String email;
	private Name name;
	private String birthdate; // DD-MM-YYYY
	private Country country;

	public Customer() {

	}

	public Customer(String email, Name name, String birthdate, Country country) {
		super();
		this.email = email;
		this.name = name;
		this.birthdate = birthdate;
		this.country = country;
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

	@Override
	public String toString() {
		return "Customer [email=" + email + ", name=" + name + ", birthdate=" + birthdate + ", country=" + country
				+ "]";
	}

}
