package com.in.app.exception;

public class BadRequestException extends RuntimeException {

	public BadRequestException(String message) {
		super("Bad Request : " + message);
	}
}
