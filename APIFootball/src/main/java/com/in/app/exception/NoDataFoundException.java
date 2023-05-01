package com.in.app.exception;

public class NoDataFoundException extends RuntimeException {
	public NoDataFoundException(String message) {
		super(String.format(message));
	}
}
