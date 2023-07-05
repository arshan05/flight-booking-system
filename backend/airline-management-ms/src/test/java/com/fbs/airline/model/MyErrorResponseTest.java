package com.fbs.airline.model;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class MyErrorResponseTest {

    @Test
    public void testConstructorAndGetters() {
        LocalDateTime timestamp = LocalDateTime.now();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "Something went wrong.";

        MyErrorResponse errorResponse = new MyErrorResponse(timestamp, status, message);

        Assertions.assertEquals(timestamp, errorResponse.getTimestamp());
        Assertions.assertEquals(status, errorResponse.getStatus());
        Assertions.assertEquals(message, errorResponse.getMessage());
    }

    @Test
    public void testSetters() {
        LocalDateTime timestamp = LocalDateTime.now();
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = "Resource not found.";

        MyErrorResponse errorResponse = new MyErrorResponse();
        errorResponse.setTimestamp(timestamp);
        errorResponse.setStatus(status);
        errorResponse.setMessage(message);

        Assertions.assertEquals(timestamp, errorResponse.getTimestamp());
        Assertions.assertEquals(status, errorResponse.getStatus());
        Assertions.assertEquals(message, errorResponse.getMessage());
    }

    @Test
    public void testToString() {
        LocalDateTime timestamp = LocalDateTime.now();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Invalid input.";

        MyErrorResponse errorResponse = new MyErrorResponse(timestamp, status, message);

        String expectedString = "MyErrorResponse(timestamp=" + timestamp + ", status=" + status +
                 ", message=" + message + ")";
        Assertions.assertEquals(expectedString, errorResponse.toString());
    }
}
