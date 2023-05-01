package com.in.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in.app.configuration.Error;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = CountryNotFoundException.class)
	public ResponseEntity<Error> countryNotFoundExceptionHandler(RuntimeException r, WebRequest w) {
		Error error =  new Error();
		error.setErrorMessage("Country Not Found for the requested data");
		error.setErrorName("CoutryNotFound");
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<Error> countryNotFoundExceptionHandler1(RuntimeException r, WebRequest w) {
		Error error =  new Error();
		error.setErrorMessage(r.toString());
		error.setErrorName("BadRequest");
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
}
