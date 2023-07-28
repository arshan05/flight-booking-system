package com.fbs.checkin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookingDetailsException extends Exception{
	public BookingDetailsException(String message) {
		super(message);
	}
}
