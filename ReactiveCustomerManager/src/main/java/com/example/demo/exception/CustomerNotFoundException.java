package com.example.demo.exception;

import javax.xml.ws.http.HTTPException;

import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends HTTPException {

	private String m;
	
	public CustomerNotFoundException() {
		super(404);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
