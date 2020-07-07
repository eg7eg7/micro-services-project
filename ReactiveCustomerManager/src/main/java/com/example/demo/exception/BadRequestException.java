package com.example.demo.exception;

import javax.xml.ws.http.HTTPException;

public class BadRequestException extends HTTPException {

	private String m;
	
	public BadRequestException(String message) {
		super(401);
		this.setErrorMessage(message);
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
