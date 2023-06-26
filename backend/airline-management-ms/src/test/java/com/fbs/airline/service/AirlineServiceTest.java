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

import com.fbs.airline.exception.AirlineException;
import com.fbs.airline.model.Airline;
import com.fbs.airline.repository.AirlineRepository;

class AirlineServiceTest {

	@Mock
	private AirlineRepository airlineRepository;

	@Mock
	private Logger logger;

	@InjectMocks
	private AirlineService airlineService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllAirlines_WithExistingAirlines_ReturnsAirlinesList() throws AirlineException {
		// Arrange
		List<Airline> airlines = new ArrayList<>();
		airlines.add(new Airline("1", "ABC", "Test Airline", null));
		airlines.add(new Airline("2", "ABC2", "Test Airline2", null));

		when(airlineRepository.findAll()).thenReturn(airlines);

		// Act
		List<Airline> result = airlineService.getAllAirlines();

		// Assert
		assertEquals(airlines.size(), result.size());
		verify(logger).info("Airlines retrieved successfully. {} items found", airlines.size());
	}

	@Test
    void getAllAirlines_WithNoAirlines_ThrowsAirlineException() {
        // Arrange
        when(airlineRepository.findAll()).thenReturn(new ArrayList<>());

        // Act & Assert
        assertThrows(AirlineException.class, () -> airlineService.getAllAirlines());
        verify(logger).error("Error: No Airlines Found");
    }

	@Test
	void addAirline_WithNonExistingAirline_AddsAndReturnsAirline() throws AirlineException {
		// Arrange
        Airline airline = new Airline("1", "ABC", "Test Airline", null);
		when(airlineRepository.existsByAirlineName(airline.getAirlineName())).thenReturn(false);
		when(airlineRepository.save(airline)).thenReturn(airline);

		// Act
		Airline result = airlineService.addAirline(airline);

		// Assert
		assertEquals(airline, result);
		verify(logger).info("airline added successfully");
	}

	@Test
	void addAirline_WithExistingAirline_ThrowsAirlineException() {
		// Arrange
		Airline airline = new Airline("1", "ABC", "Test Airline", null);
		when(airlineRepository.existsByAirlineName(airline.getAirlineName())).thenReturn(true);

		// Act & Assert
//		verify(logger).error("Error: Airline by this name already exists");
		assertThrows(AirlineException.class, () -> airlineService.addAirline(airline));
	}

	@Test
	void updateAirline_WithExistingAirline_UpdatesAndReturnsAirline() throws AirlineException {
		// Arrange
		Airline airline = new Airline("1", "ABC", "Test Airline", null);
		when(airlineRepository.existsById(airline.getId())).thenReturn(true);
		when(airlineRepository.save(airline)).thenReturn(airline);

		// Act
		Airline result = airlineService.updateAirline(airline);

		// Assert
		assertEquals(airline, result);
		verify(logger).info("airline updated successfully");
	}

	@Test
	void updateAirline_WithNonExistingAirline_ThrowsAirlineException() {
		// Arrange

		Airline airline = new Airline("1", "ABC", "Test Airline", null);
		when(airlineRepository.existsById(airline.getId())).thenReturn(false);

		// Act & Assert
		assertThrows(AirlineException.class, () -> airlineService.updateAirline(airline));
		verify(logger).error("Error: Airline doesn't exist");
	}

	@Test
	void deleteAirline_WithExistingAirline_DeletesAndReturnsTrue() throws AirlineException {
		// Arrange
        Airline airline = new Airline("1", "ABC", "Test Airline", null);

		when(airlineRepository.existsById(airline.getId())).thenReturn(true);

		// Act
		boolean result = airlineService.deleteAirline(airline);

		// Assert
		assertTrue(result);
		verify(airlineRepository).deleteById(airline.getId());
		verify(logger).info("airline deleted successfully");
	}

	@Test
	void deleteAirline_WithNonExistingAirline_ThrowsAirlineException() {
		// Arrange
        Airline airline = new Airline("1", "ABC", "Test Airline", null);
		when(airlineRepository.existsById(airline.getId())).thenReturn(false);

		// Act & Assert
		assertThrows(AirlineException.class, () -> airlineService.deleteAirline(airline));
		verify(logger).error("Error: Airline doesn't exist");
	}
}
