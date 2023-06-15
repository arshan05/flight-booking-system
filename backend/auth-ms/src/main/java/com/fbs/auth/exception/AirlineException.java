package com.fbs.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AirlineException extends Exception{
	

	public AirlineException(String message) {
		super(message);
	}

}
