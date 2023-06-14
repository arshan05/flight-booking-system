package com.fbs.airline.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fbs.airline.exception.AirlineException;
import com.fbs.airline.exception.AirportException;
import com.fbs.airline.exception.FlightException;
import com.fbs.airline.exception.ScheduleException;
import com.fbs.airline.model.MyErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ AirlineException.class })
	public ResponseEntity<MyErrorResponse> handleAirlineNotFound(AirlineException ex) {
		MyErrorResponse error = new MyErrorResponse();
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setMessage(ex.getMessage());
		error.setReason("Airline might not exist...");
		return new ResponseEntity<MyErrorResponse>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ AirportException.class })
	public ResponseEntity<MyErrorResponse> handleAirportNotFound(AirlineException ex) {
		MyErrorResponse error = new MyErrorResponse();
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setMessage(ex.getMessage());
		error.setReason("Airport might not exist...");
		return new ResponseEntity<MyErrorResponse>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ FlightException.class })
	public ResponseEntity<MyErrorResponse> handleFlightNotFound(AirlineException ex) {
		MyErrorResponse error = new MyErrorResponse();
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setMessage(ex.getMessage());
		error.setReason("Flight might not exist...");
		return new ResponseEntity<MyErrorResponse>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ ScheduleException.class })
	public ResponseEntity<MyErrorResponse> handleScheduleNotFound(AirlineException ex) {
		MyErrorResponse error = new MyErrorResponse();
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setMessage(ex.getMessage());
		error.setReason("Schedule might not exist...");
		return new ResponseEntity<MyErrorResponse>(error, HttpStatus.NOT_FOUND);
	}

//	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
//	public ResponseEntity<MyErrorResponse> handleBadRequest(MethodArgumentTypeMismatchException ex) {
//		MyErrorResponse error = new MyErrorResponse();
//		error.setTimestamp(LocalDateTime.now());
//		error.setStatus(HttpStatus.BAD_REQUEST);
//		error.setMessage(ex.getMessage());
//		error.setReason("Wrong url/method typed ....");
//		return new ResponseEntity<MyErrorResponse>(error, HttpStatus.BAD_REQUEST);
//	}

//	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
//	public ResponseEntity<MyErrorResponse> handleMethodNotSupportException(HttpRequestMethodNotSupportedException ex) {
//		MyErrorResponse error = new MyErrorResponse();
//		error.setTimestamp(LocalDateTime.now());
//		error.setStatus(HttpStatus.METHOD_NOT_ALLOWED);
//		error.setMessage(ex.getMessage());
//		error.setReason("Wrong method selected....");
//		return new ResponseEntity<MyErrorResponse>(error, HttpStatus.METHOD_NOT_ALLOWED);
//	}

//	@ExceptionHandler({ Exception.class })
//	public ResponseEntity<Object> handleException(Exception ex) {
//		Map<String, Object> body = new LinkedHashMap<String, Object>();
//		body.put("timestamp", LocalDateTime.now());
//		body.put("Status", HttpStatus.NOT_ACCEPTABLE);
//		body.put("Message", ex.getMessage());
//		body.put("Reason", "Wrong url typed....");
//		return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
//	}
}
