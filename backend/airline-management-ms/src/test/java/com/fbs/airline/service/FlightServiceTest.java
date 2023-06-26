package com.fbs.airline.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import com.fbs.airline.exception.FlightException;
import com.fbs.airline.model.Airline;
import com.fbs.airline.model.Flight;
import com.fbs.airline.proxy.FareProxy;
import com.fbs.airline.repository.AirlineRepository;
import com.fbs.airline.repository.FlightRepository;

class FlightServiceTest {

	@Mock
	private FlightRepository flightRepository;
	
	@Mock
	private AirlineRepository airlineRepository;

	@Mock
	private Logger logger;

	@InjectMocks
	private FlightService flightService;

	@Mock
    private FareProxy fareProxy;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllFlights_WithExistingFlights_ReturnsFlightsList() throws FlightException {
		List<Flight> flights = new ArrayList<>();
		flights.add(new Flight("1", "fl01", null, 90, 6, null, null));
		flights.add(new Flight("2", "fl02", null, 90, 6, null, null));

		when(flightRepository.findAll()).thenReturn(flights);

		List<Flight> result = flightService.getAllFlights();

		assertEquals(flights.size(), result.size());
		verify(logger).info("Flights retrieved successfully. {} items found", flights.size());
	}

	@Test
    void getAllFlights_WithNoFlights_ThrowsFlightException() {
        when(flightRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(FlightException.class, () -> flightService.getAllFlights());
        verify(logger).error("Error: No Flights Found");
    }

	@Test
	void addFlight_WithNonExistingFlight_AddsAndReturnsFlight() throws FlightException {
		Airline airline = new Airline("1", "ABC", "Test Airline", null);
		Flight flight = new Flight("1", "fl01", airline, 90, 6, null, null);
		
		when(flightRepository.existsByFlightNumber(flight.getFlightNumber())).thenReturn(false);
        when(fareProxy.setFlightSeat(flight)).thenReturn(flight);
        when(flightRepository.save(flight)).thenReturn(flight);

        // Act
        Flight result = flightService.addFlight(flight);

        // Assert
        assertEquals(flight, result);
        verify(flightRepository).existsByFlightNumber(flight.getFlightNumber());
        verify(fareProxy).setFlightSeat(flight);
        verify(flightRepository).save(flight);
        verify(logger).info("flight added successfully");
	}

	@Test
	void addFlight_WithExistingFlight_ThrowsFlightException() {
		Airline airline = new Airline("1", "ABC", "Test Airline", null);
		Flight flight = new Flight("1", "fl01", airline, 90, 6, null, null);
		when(flightRepository.existsByFlightNumber(flight.getFlightNumber())).thenReturn(true);
		
		assertThrows(FlightException.class, () -> flightService.addFlight(flight));
		verify(logger).error("Error: Flight by this number already exists");
	}

	@Test
	void updateFlight_WithExistingFlight_UpdatesAndReturnsFlight() throws FlightException {
		Flight flight = new Flight("1", "fl01", null, 90, 6, null, null);
		when(flightRepository.existsById(flight.getId())).thenReturn(true);
		when(flightRepository.save(flight)).thenReturn(flight);

		Flight result = flightService.updateFlight(flight);

		assertEquals(flight, result);
		verify(logger).info("flight updated successfully");
	}

	@Test
	void updateFlight_WithNonExistingFlight_ThrowsFlightException() {
		Flight flight = new Flight("1", "fl01", null, 90, 6, null, null);
		when(flightRepository.existsById(flight.getId())).thenReturn(false);

		assertThrows(FlightException.class, () -> flightService.updateFlight(flight));
		verify(logger).error("Error: Flight doesn't exist");
	}

	@Test
	void deleteFlight_WithExistingFlight_DeletesAndReturnsTrue() throws FlightException {
		Flight flight = new Flight("1", "fl01", null, 90, 6, null, null);
		when(flightRepository.existsById(flight.getId())).thenReturn(true);

		boolean result = flightService.deleteFlight(flight);

		assertTrue(result);
		verify(flightRepository).deleteById(flight.getId());
		verify(logger).info("flight deleted successfully");
	}

	@Test
	void deleteFlight_WithNonExistingFlight_ThrowsFlightException() {
		Flight flight = new Flight("1", "fl01", null, 90, 6, null, null);
		when(flightRepository.existsById(flight.getId())).thenReturn(false);

		assertThrows(FlightException.class, () -> flightService.deleteFlight(flight));
		verify(logger).error("Error: Flight doesn't exist");
	}
}
