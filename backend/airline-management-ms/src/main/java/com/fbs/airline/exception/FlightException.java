package com.fbs.airline.exception;

public class FlightException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FlightException(){
		super();
	}
	
	public FlightException(String message) {
		super(message);
	}

}
