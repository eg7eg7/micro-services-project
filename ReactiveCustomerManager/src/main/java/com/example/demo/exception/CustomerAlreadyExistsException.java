package com.example.demo.exception;

import javax.xml.ws.http.HTTPException;

import org.springframework.http.HttpStatus;

public class CustomerAlreadyExistsException extends HTTPException {

	
	public CustomerAlreadyExistsException() {
		super(500);
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
