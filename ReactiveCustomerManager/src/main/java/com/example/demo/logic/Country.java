package com.example.demo.logic;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "COUNTRIES")
public class Country {
	@Id
	private String countryCode;
	private String countryName;
	
	public Country() {
		
	}
	
	public Country(String countryCode, String countryName) {
		super();
		this.setCountryCode(countryCode);
		this.setCountryName(countryName);
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return "Country [countryCode=" + countryCode + ", countryName=" + countryName + "]";
	}
	
	
}
