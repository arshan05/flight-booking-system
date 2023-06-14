package com.fbs.airline.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ScheduleException extends Exception{
	public ScheduleException(String message) {
		super(message);
	}

}
