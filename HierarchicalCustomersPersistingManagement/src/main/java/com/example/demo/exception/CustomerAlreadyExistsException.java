package com.example.demo.exception;

import javax.xml.ws.http.HTTPException;

public class CustomerAlreadyExistsException extends HTTPException {

	private String m;

	public CustomerAlreadyExistsException() {
		super(500);
		m = "Customer already exists";
	}

	public String getErrorMessage() {
		return m;
	}

	public void setErrorMessage(String m) {
		this.m = m;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
