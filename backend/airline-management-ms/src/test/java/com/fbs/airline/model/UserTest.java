package com.fbs.airline.model;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class UserTest {

    @Test
    public void testValidUser() {
        User user = new User("john.doe@example.com", "password123", "ROLE_USER");

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidUsername() {
        User user = new User("", "password123", "ROLE_USER");

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    public void testInvalidPassword() {
        User user = new User("john.doe@example.com", "123", "ROLE_USER");

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    public void testValidRole() {
        User user = new User("john.doe@example.com", "password123", "ROLE_USER");

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        

        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidRole() {
        User user = new User("john.doe@example.com", "password123", "");

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        Assertions.assertFalse(violations.isEmpty());
    }
    
    @Test
    public void testDefaultConstructor() {
        User user = new User();

        Assertions.assertNull(user.getUsername());
        Assertions.assertNull(user.getPassword());
        Assertions.assertNull(user.getRole());
    }

    @Test
    public void testGetUsername() {
        User user = new User();
        user.setUsername("john.doe@example.com");

        Assertions.assertEquals("john.doe@example.com", user.getUsername());
    }

    @Test
    public void testSetUsername() {
        User user = new User();
        user.setUsername("john.doe@example.com");

        Assertions.assertEquals("john.doe@example.com", user.getUsername());
    }

    @Test
    public void testGetPassword() {
        User user = new User();
        user.setPassword("password123");

        Assertions.assertEquals("password123", user.getPassword());
    }

    @Test
    public void testSetPassword() {
        User user = new User();
        user.setPassword("password123");

        Assertions.assertEquals("password123", user.getPassword());
    }

    @Test
    public void testGetRole() {
        User user = new User();
        user.setRole("ROLE_USER");

        Assertions.assertEquals("ROLE_USER", user.getRole());
    }

    @Test
    public void testSetRole() {
        User user = new User();
        user.setRole("ROLE_USER");

        Assertions.assertEquals("ROLE_USER", user.getRole());
    }
}
