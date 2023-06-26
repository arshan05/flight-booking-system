package com.fbs.airline.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FlightException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FlightException(String message) {
		super(message);
	}

}
