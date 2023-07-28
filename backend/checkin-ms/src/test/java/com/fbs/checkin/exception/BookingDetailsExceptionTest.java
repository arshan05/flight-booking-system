package com.fbs.checkin.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BookingDetailsExceptionTest {

	@Test
	public void testConstructorAndGetMessage() {
		String errorMessage = "BookingDetails not found.";

		BookingDetailsException exception = new BookingDetailsException(errorMessage);

		Assertions.assertEquals(errorMessage, exception.getMessage());
	}

	@Test
	public void testResponseStatusAnnotation() {
		ResponseStatus responseStatus = BookingDetailsException.class.getAnnotation(ResponseStatus.class);

		Assertions.assertNotNull(responseStatus);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, responseStatus.value());
	}
}
