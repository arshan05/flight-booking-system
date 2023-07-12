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

import com.fbs.airline.exception.AirportException;
import com.fbs.airline.model.Airport;
import com.fbs.airline.service.AuthService;
import com.fbs.airline.service.IAirportService;

class AirportControllerTest {

	@Mock
	IAirportService airportService;

	@Mock
	AuthService authService;

	@InjectMocks
	AirportController airportController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
    void testAddAirport() throws AirportException {
        // Mock the AuthService to return true for session validity
        when(authService.isSessionValid(anyString())).thenReturn(true);

        Airport airport = new Airport();
        when(airportService.addAirport(any(Airport.class))).thenReturn(airport);

        ResponseEntity<Airport> response = airportController.addAirport("dummy-cookie", airport);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(airport, response.getBody());

    }

	@Test
    void testGetAllAirports() throws AirportException {
        when(authService.isSessionValid(anyString())).thenReturn(true);

        List<Airport> airports = new ArrayList<>();
        when(airportService.getAllAirports()).thenReturn(airports);

        ResponseEntity<List<Airport>> response = airportController.getAllAirports("dummy-cookie");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(airports, response.getBody());

    }

	@Test
	public void testDeleteAirport() throws AirportException {
		Airport airport = new Airport();
		airport.setCode("AA");
		airport.setAirportName("American Airports");

		when(airportService.deleteAirport(airport)).thenReturn(true);
		when(authService.isSessionValid(anyString())).thenReturn(true);

		ResponseEntity<String> response = airportController.deleteAirine("dummy-cookie", airport);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Successfully Deleted", response.getBody());
	}

	@Test
	public void testUpdateAirport() throws AirportException {
		Airport airport = new Airport();
		airport.setCode("AA");
		airport.setAirportName("American Airports");

		when(airportService.updateAirport(airport)).thenReturn(airport);
		when(authService.isSessionValid(anyString())).thenReturn(true);

		ResponseEntity<Airport> response = airportController.updateAirport("dummy-cookie", airport);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(airport, response.getBody());
	}

	@Test
    void testAddAirportThrowsUnauthorizedException() throws AirportException {
        when(authService.isSessionValid(anyString())).thenReturn(false);

        Airport airport = new Airport();

        assertThrows(ResponseStatusException.class, () -> {
            airportController.addAirport("dummy-cookie", airport);
        });

    }

	@Test
    void testGetAllAirportsThrowsUnauthorizedException() throws AirportException {
        when(authService.isSessionValid(anyString())).thenReturn(false);
        
        assertThrows(ResponseStatusException.class, () -> {
            airportController.getAllAirports("dummy-cookie");
        });

    }

	@Test
	public void testDeleteAirportThrowsUnauthorizedException() throws AirportException {
		Airport airport = new Airport();

		when(authService.isSessionValid(anyString())).thenReturn(false);
		assertThrows(ResponseStatusException.class, () -> {
			airportController.deleteAirine("dummy-cookie", airport);
		});
	}

	@Test
	public void testUpdateAirportThrowsUnauthorizedException() throws AirportException {
		Airport airport = new Airport();

		when(authService.isSessionValid(anyString())).thenReturn(false);
		assertThrows(ResponseStatusException.class, () -> {
			airportController.updateAirport("dummy-cookie", airport);
		});
	}

}
