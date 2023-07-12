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

import com.fbs.airline.exception.FlightException;
import com.fbs.airline.model.Flight;
import com.fbs.airline.service.AuthService;
import com.fbs.airline.service.IFlightService;

class FlightControllerTest {


	@Mock
	IFlightService flightService;

	@Mock
	AuthService authService;

	@InjectMocks
	FlightController flightController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
    void testAddFlight() throws FlightException {
        // Mock the AuthService to return true for session validity
        when(authService.isSessionValid(anyString())).thenReturn(true);

        Flight flight = new Flight();
        when(flightService.addFlight(any(Flight.class))).thenReturn(flight);

        ResponseEntity<Flight> response = flightController.addFlight("dummy-cookie", flight);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(flight, response.getBody());

    }

	@Test
    void testGetAllFlights() throws FlightException {
        when(authService.isSessionValid(anyString())).thenReturn(true);

        List<Flight> flights = new ArrayList<>();
        when(flightService.getAllFlights()).thenReturn(flights);

        ResponseEntity<List<Flight>> response = flightController.getAllFlights("dummy-cookie");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(flights, response.getBody());

    }

	@Test
	public void testDeleteFlight() throws FlightException {
		Flight flight = new Flight();

		when(flightService.deleteFlight(flight)).thenReturn(true);
		when(authService.isSessionValid(anyString())).thenReturn(true);

		ResponseEntity<String> response = flightController.deleteAirine("dummy-cookie", flight);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Successfully Deleted", response.getBody());
	}

	@Test
	public void testUpdateFlight() throws FlightException {
		Flight flight = new Flight();

		when(flightService.updateFlight(flight)).thenReturn(flight);
		when(authService.isSessionValid(anyString())).thenReturn(true);

		ResponseEntity<Flight> response = flightController.updateFlight("dummy-cookie", flight);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(flight, response.getBody());
	}

	@Test
    void testAddFlightThrowsUnauthorizedException() throws FlightException {
        when(authService.isSessionValid(anyString())).thenReturn(false);

        Flight flight = new Flight();

        assertThrows(ResponseStatusException.class, () -> {
            flightController.addFlight("dummy-cookie", flight);
        });

    }

	@Test
    void testGetAllFlightsThrowsUnauthorizedException() throws FlightException {
        when(authService.isSessionValid(anyString())).thenReturn(false);
        
        assertThrows(ResponseStatusException.class, () -> {
            flightController.getAllFlights("dummy-cookie");
        });

    }

	@Test
	public void testDeleteFlightThrowsUnauthorizedException() throws FlightException {
		Flight flight = new Flight();

		when(authService.isSessionValid(anyString())).thenReturn(false);
		assertThrows(ResponseStatusException.class, () -> {
			flightController.deleteAirine("dummy-cookie", flight);
		});
	}

	@Test
	public void testUpdateFlightThrowsUnauthorizedException() throws FlightException {
		Flight flight = new Flight();

		when(authService.isSessionValid(anyString())).thenReturn(false);
		assertThrows(ResponseStatusException.class, () -> {
			flightController.updateFlight("dummy-cookie", flight);
		});
	}


}
