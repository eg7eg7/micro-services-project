package com.example.demo.exception;

import javax.xml.ws.http.HTTPException;

public class CustomerNotFoundException extends HTTPException {


	public CustomerNotFoundException() {
		super(404);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
