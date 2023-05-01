package com.in.app.exception;

public class CountryNotFoundException extends RuntimeException {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CountryNotFoundException(String message) {
	        super(message);
	    }
}
