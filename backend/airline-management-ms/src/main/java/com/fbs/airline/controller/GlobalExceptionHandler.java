package com.fbs.airline.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fbs.airline.exception.AirlineException;
import com.fbs.airline.exception.AirportException;
import com.fbs.airline.exception.FlightException;
import com.fbs.airline.exception.ScheduleException;
import com.fbs.airline.model.MyErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@Autowired
	MyErrorResponse error;

	@ExceptionHandler({ AirlineException.class })
	public ResponseEntity<MyErrorResponse> handleAirlineNotFound(AirlineException ex) {
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setMessage(ex.getMessage());
		error.setReason("Airline might not exist...");
		return new ResponseEntity<MyErrorResponse>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ AirportException.class })
	public ResponseEntity<MyErrorResponse> handleAirportNotFound(AirlineException ex) {
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setMessage(ex.getMessage());
		error.setReason("Airport might not exist...");
		return new ResponseEntity<MyErrorResponse>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ FlightException.class })
	public ResponseEntity<MyErrorResponse> handleFlightNotFound(AirlineException ex) {
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setMessage(ex.getMessage());
		error.setReason("Flight might not exist...");
		return new ResponseEntity<MyErrorResponse>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ ScheduleException.class })
	public ResponseEntity<MyErrorResponse> handleScheduleNotFound(AirlineException ex) {
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setMessage(ex.getMessage());
		error.setReason("Schedule might not exist...");
		return new ResponseEntity<MyErrorResponse>(error, HttpStatus.NOT_FOUND);
	}

}
