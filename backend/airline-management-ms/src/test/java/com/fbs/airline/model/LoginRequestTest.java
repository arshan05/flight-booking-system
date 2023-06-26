package com.fbs.airline.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class LoginRequestTest {

    @Test
    public void testValidUsername() {
        LoginRequest request = new LoginRequest();
        request.setUsername("john.doe@example.com");
        request.setPassword("password123");

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(request);

        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidUsername() {
        LoginRequest request = new LoginRequest();
        request.setUsername("");
        request.setPassword("password123");

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(request);

        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    public void testValidPassword() {
        LoginRequest request = new LoginRequest();
        request.setUsername("john.doe@example.com");
        request.setPassword("password123");

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(request);

        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidPassword() {
        LoginRequest request = new LoginRequest();
        request.setUsername("john.doe@example.com");
        request.setPassword("123");

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(request);

        Assertions.assertFalse(violations.isEmpty());
    }
    
    @Test
	void testConstructorAndGetters() {
    	LoginRequest loginRequest = new LoginRequest("john.doe@example.com","password123");

		assertEquals("john.doe@example.com", loginRequest.getUsername());
		assertEquals("password123", loginRequest.getPassword());
	}
    
    @Test
	void testToString() {
    	LoginRequest loginRequest = new LoginRequest("john.doe@example.com","password123");
    	
		String expectedString = "LoginRequest(username=john.doe@example.com, password=password123)"; 
		assertEquals(expectedString, loginRequest.toString());
	}

	@Test
	void testAllArgsConstructor() {
		LoginRequest loginRequest = new LoginRequest("john.doe@example.com","password123");

		assertEquals("john.doe@example.com", loginRequest.getUsername());
		assertEquals("password123", loginRequest.getPassword());
	}
}
