package com.fbs.airline.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class FlightExceptionTest {

    @Test
    public void testConstructorAndGetMessage() {
        String errorMessage = "Flight not found.";

        FlightException exception = new FlightException(errorMessage);

        Assertions.assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void testResponseStatusAnnotation() {
        ResponseStatus responseStatus = FlightException.class.getAnnotation(ResponseStatus.class);

        Assertions.assertNotNull(responseStatus);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseStatus.value());
    }
}

