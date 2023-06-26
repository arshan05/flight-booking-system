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

import com.fbs.airline.exception.AirportException;
import com.fbs.airline.model.Airport;
import com.fbs.airline.repository.AirportRepository;

class AirportServiceTest {

	@Mock
	private AirportRepository airportRepository;

	@Mock
	private Logger logger;

	@InjectMocks
	private AirportService airportService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllAirports_WithExistingAirports_ReturnsAirportsList() throws AirportException {
		List<Airport> airports = new ArrayList<>();
		airports.add(new Airport("1", null, "BLR", "Kempegowda International Airport", null));
		airports.add(new Airport("2", null, "BLR", "Kempegowda International Airport", null));

		when(airportRepository.findAll()).thenReturn(airports);

		List<Airport> result = airportService.getAllAirports();

		assertEquals(airports.size(), result.size());
		verify(logger).info("Airports retrieved successfully. {} items found", airports.size());
	}

	@Test
    void getAllAirports_WithNoAirports_ThrowsAirportException() {
        when(airportRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(AirportException.class, () -> airportService.getAllAirports());
        verify(logger).error("Error: No Airports Found");
    }

	@Test
	void addAirport_WithNonExistingAirport_AddsAndReturnsAirport() throws AirportException {
		Airport airport = new Airport("1", null, "BLR", "Kempegowda International Airport", null);
		when(airportRepository.existsByAirportName(airport.getAirportName())).thenReturn(false);
		when(airportRepository.save(airport)).thenReturn(airport);

		Airport result = airportService.addAirport(airport);

		assertEquals(airport, result);
		verify(logger).info("airport added successfully");
	}

	@Test
	void addAirport_WithExistingAirport_ThrowsAirportException() {
		Airport airport = new Airport("1", null, "BLR", "Kempegowda International Airport", null);
		when(airportRepository.existsByAirportName(airport.getAirportName())).thenReturn(true);

		assertThrows(AirportException.class, () -> airportService.addAirport(airport));
		verify(logger).error("Error: Airport by this name already exists");
	}

	@Test
	void updateAirport_WithExistingAirport_UpdatesAndReturnsAirport() throws AirportException {
		Airport airport = new Airport("1", null, "BLR", "Kempegowda International Airport", null);
		when(airportRepository.existsById(airport.getId())).thenReturn(true);
		when(airportRepository.save(airport)).thenReturn(airport);

		Airport result = airportService.updateAirport(airport);

		assertEquals(airport, result);
		verify(logger).info("airport updated successfully");
	}

	@Test
	void updateAirport_WithNonExistingAirport_ThrowsAirportException() {
		Airport airport = new Airport("1", null, "BLR", "Kempegowda International Airport", null);
		when(airportRepository.existsById(airport.getId())).thenReturn(false);

		assertThrows(AirportException.class, () -> airportService.updateAirport(airport));
		verify(logger).error("Error: Airport doesn't exist");
	}

	@Test
	void deleteAirport_WithExistingAirport_DeletesAndReturnsTrue() throws AirportException {
		Airport airport = new Airport("1", null, "BLR", "Kempegowda International Airport", null);
		when(airportRepository.existsById(airport.getId())).thenReturn(true);

		boolean result = airportService.deleteAirport(airport);

		assertTrue(result);
		verify(airportRepository).deleteById(airport.getId());
		verify(logger).info("airport deleted successfully");
	}

	@Test
	void deleteAirport_WithNonExistingAirport_ThrowsAirportException() {
		Airport airport = new Airport("1", null, "BLR", "Kempegowda International Airport", null);
		when(airportRepository.existsById(airport.getId())).thenReturn(false);

		assertThrows(AirportException.class, () -> airportService.deleteAirport(airport));
		verify(logger).error("Error: Airport doesn't exist");
	}
}
