package com.fbs.airline.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class AirlineExceptionTest {

    @Test
    public void testConstructorAndGetMessage() {
        String errorMessage = "Airline not found.";

        AirlineException exception = new AirlineException(errorMessage);

        Assertions.assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void testResponseStatusAnnotation() {
        ResponseStatus responseStatus = AirlineException.class.getAnnotation(ResponseStatus.class);

        Assertions.assertNotNull(responseStatus);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseStatus.value());
    }
}
