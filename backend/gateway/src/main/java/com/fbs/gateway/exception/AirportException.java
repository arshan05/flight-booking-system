package com.fbs.gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AirportException extends Exception{
	public AirportException(String message) {
		super(message);
	}

}
