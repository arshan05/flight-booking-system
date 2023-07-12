package com.fbs.airline.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
import org.springframework.web.server.ResponseStatusException;

import com.fbs.airline.exception.AirlineException;
import com.fbs.airline.model.Airline;
import com.fbs.airline.service.AuthService;
import com.fbs.airline.service.IAirlineService;

class AirlineControllerTest {

	@Mock
	IAirlineService airlineService;

	@Mock
	AuthService authService;

	@InjectMocks
	AirlineController airlineController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
    void testAddAirline() throws AirlineException {
        // Mock the AuthService to return true for session validity
        when(authService.isSessionValid(anyString())).thenReturn(true);

        Airline airline = new Airline();
        when(airlineService.addAirline(any(Airline.class))).thenReturn(airline);

        ResponseEntity<Airline> response = airlineController.addAirline("dummy-cookie", airline);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(airline, response.getBody());

    }

	@Test
    void testGetAllAirlines() throws AirlineException {
        when(authService.isSessionValid(anyString())).thenReturn(true);

        List<Airline> airlines = new ArrayList<>();
        when(airlineService.getAllAirlines()).thenReturn(airlines);

        ResponseEntity<List<Airline>> response = airlineController.getAllAirlines("dummy-cookie");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(airlines, response.getBody());

    }

	@Test
	public void testDeleteAirline() throws AirlineException {
		Airline airline = new Airline();
		airline.setCode("AA");
		airline.setAirlineName("American Airlines");

		when(airlineService.deleteAirline(airline)).thenReturn(true);
		when(authService.isSessionValid(anyString())).thenReturn(true);

		ResponseEntity<String> response = airlineController.deleteAirine("dummy-cookie", airline);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Successfully Deleted", response.getBody());
	}

	@Test
	public void testUpdateAirline() throws AirlineException {
		Airline airline = new Airline();
		airline.setCode("AA");
		airline.setAirlineName("American Airlines");

		when(airlineService.updateAirline(airline)).thenReturn(airline);
		when(authService.isSessionValid(anyString())).thenReturn(true);

		ResponseEntity<Airline> response = airlineController.updateAirline("dummy-cookie", airline);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(airline, response.getBody());
	}

	@Test
    void testAddAirlineThrowsUnauthorizedException() throws AirlineException {
        when(authService.isSessionValid(anyString())).thenReturn(false);

        Airline airline = new Airline();

        assertThrows(ResponseStatusException.class, () -> {
            airlineController.addAirline("dummy-cookie", airline);
        });

    }

	@Test
    void testGetAllAirlinesThrowsUnauthorizedException() throws AirlineException {
        when(authService.isSessionValid(anyString())).thenReturn(false);
        
        assertThrows(ResponseStatusException.class, () -> {
            airlineController.getAllAirlines("dummy-cookie");
        });

    }

	@Test
	public void testDeleteAirlineThrowsUnauthorizedException() throws AirlineException {
		Airline airline = new Airline();

		when(authService.isSessionValid(anyString())).thenReturn(false);
		assertThrows(ResponseStatusException.class, () -> {
			airlineController.deleteAirine("dummy-cookie", airline);
		});
	}

	@Test
	public void testUpdateAirlineThrowsUnauthorizedException() throws AirlineException {
		Airline airline = new Airline();

		when(authService.isSessionValid(anyString())).thenReturn(false);
		assertThrows(ResponseStatusException.class, () -> {
			airlineController.updateAirline("dummy-cookie", airline);
		});
	}
}
