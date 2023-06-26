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
        String reason = "Internal Server Error";
        String message = "Something went wrong.";

        MyErrorResponse errorResponse = new MyErrorResponse(timestamp, status, reason, message);

        Assertions.assertEquals(timestamp, errorResponse.getTimestamp());
        Assertions.assertEquals(status, errorResponse.getStatus());
        Assertions.assertEquals(reason, errorResponse.getReason());
        Assertions.assertEquals(message, errorResponse.getMessage());
    }

    @Test
    public void testSetters() {
        LocalDateTime timestamp = LocalDateTime.now();
        HttpStatus status = HttpStatus.NOT_FOUND;
        String reason = "Not Found";
        String message = "Resource not found.";

        MyErrorResponse errorResponse = new MyErrorResponse();
        errorResponse.setTimestamp(timestamp);
        errorResponse.setStatus(status);
        errorResponse.setReason(reason);
        errorResponse.setMessage(message);

        Assertions.assertEquals(timestamp, errorResponse.getTimestamp());
        Assertions.assertEquals(status, errorResponse.getStatus());
        Assertions.assertEquals(reason, errorResponse.getReason());
        Assertions.assertEquals(message, errorResponse.getMessage());
    }

    @Test
    public void testToString() {
        LocalDateTime timestamp = LocalDateTime.now();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String reason = "Bad Request";
        String message = "Invalid input.";

        MyErrorResponse errorResponse = new MyErrorResponse(timestamp, status, reason, message);

        String expectedString = "MyErrorResponse(timestamp=" + timestamp + ", status=" + status +
                ", reason=" + reason + ", message=" + message + ")";
        Assertions.assertEquals(expectedString, errorResponse.toString());
    }
}
