package com.fbs.airline.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class AuthenticationResponseNullExceptionTest {

    @Test
    public void testConstructorAndGetMessage() {
        String errorMessage = "Authentication response is null.";

        AuthenticationResponseNullException exception = new AuthenticationResponseNullException(errorMessage);

        Assertions.assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void testResponseStatusAnnotation() {
        ResponseStatus responseStatus = AuthenticationResponseNullException.class.getAnnotation(ResponseStatus.class);

        Assertions.assertNotNull(responseStatus);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseStatus.value());
    }
}

