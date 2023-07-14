package com.fbs.airline.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fbs.airline.exception.AuthenticationResponseNullException;
import com.fbs.airline.model.AuthenticationResponse;
import com.fbs.airline.proxy.AuthProxy;

class AuthServiceTest {

    @InjectMocks
    AuthService authService;

    @Mock
    AuthProxy authProxy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsSessionValidWithValidRole() {
        String cookie = "test-cookie";
        AuthenticationResponse authResponse = new AuthenticationResponse();
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        authResponse.setRole(roles);

        ResponseEntity<AuthenticationResponse> responseEntity = new ResponseEntity<>(authResponse, HttpStatus.OK);
        when(authProxy.validate(cookie)).thenReturn(responseEntity);

        boolean result = authService.isSessionValid(cookie);

        assertTrue(result);
    }

    @Test
    void testIsSessionValidWithoutValidRole() {
        String cookie = "test-cookie";
        AuthenticationResponse authResponse = new AuthenticationResponse();
        List<String> roles = new ArrayList<>();
        roles.add("user");
        authResponse.setRole(roles);

        ResponseEntity<AuthenticationResponse> responseEntity = new ResponseEntity<>(authResponse, HttpStatus.OK);
        when(authProxy.validate(cookie)).thenReturn(responseEntity);

        boolean result = authService.isSessionValid(cookie);

        assertFalse(result);
    }

    @Test
    void testIsSessionValidWithNullResponse() {
        String cookie = "test-cookie";

        ResponseEntity<AuthenticationResponse> responseEntity = null;
        when(authProxy.validate(cookie)).thenReturn(responseEntity);

        assertThrows(AuthenticationResponseNullException.class, () -> {
            authService.isSessionValid(cookie);
        });

    }
}
