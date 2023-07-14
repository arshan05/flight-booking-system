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

import com.fbs.airline.exception.LocationException;
import com.fbs.airline.model.Location;
import com.fbs.airline.repository.LocationRepository;

class LocationServiceTest {

	@Mock
	private LocationRepository locationRepository;

	@Mock
	private Logger logger;

	@InjectMocks
	private LocationService locationService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllLocations_WithExistingLocations_ReturnsLocationsList() throws LocationException {
		// Arrange
		List<Location> locations = new ArrayList<>();
		locations.add(new Location("1", "place1","state1","country1"));
		locations.add(new Location("2", "place2","state2","country2"));

		when(locationRepository.findAll()).thenReturn(locations);

		// Act
		List<Location> result = locationService.getAllLocations();

		// Assert
		assertEquals(locations.size(), result.size());
		verify(logger).info("Locations retrieved successfully. {} items found", locations.size());
	}

	@Test
    void getAllLocations_WithNoLocations_ThrowsLocationException() {
        when(locationRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(LocationException.class, () -> locationService.getAllLocations());
        verify(logger).error("Error: No Locations Found");
    }

	@Test
	void addLocation_WithNonExistingLocation_AddsAndReturnsLocation() throws LocationException {
        Location location = new Location("1", "place1","state1","country1");
		when(locationRepository.existsByPlace(location.getPlace())).thenReturn(false);
		when(locationRepository.save(location)).thenReturn(location);

		Location result = locationService.addLocation(location);

		assertEquals(location, result);
		verify(logger).info("location added successfully");
	}

	@Test
	void addLocation_WithExistingLocation_ThrowsLocationException() {
		Location location = new Location("1", "ABC", "Test Location", null);
		when(locationRepository.existsByPlace(location.getPlace())).thenReturn(true);

		assertThrows(LocationException.class, () -> locationService.addLocation(location));
	}

	@Test
	void updateLocation_WithExistingLocation_UpdatesAndReturnsLocation() throws LocationException {
		Location location =new Location("1", "place1","state1","country1");
		when(locationRepository.existsById(location.getId())).thenReturn(true);
		when(locationRepository.save(location)).thenReturn(location);

		Location result = locationService.updateLocation(location);

		assertEquals(location, result);
		verify(logger).info("location updated successfully");
	}

	@Test
	void updateLocation_WithNonExistingLocation_ThrowsLocationException() {

		Location location =new Location("1", "place1","state1","country1");
		when(locationRepository.existsById(location.getId())).thenReturn(false);

		assertThrows(LocationException.class, () -> locationService.updateLocation(location));
		verify(logger).error("Error: Location doesn't exist");
	}

	@Test
	void deleteLocation_WithExistingLocation_DeletesAndReturnsTrue() throws LocationException {
		Location location =new Location("1", "place1","state1","country1");
		
		when(locationRepository.existsById(location.getId())).thenReturn(true);

		boolean result = locationService.deleteLocation(location);

		assertTrue(result);
		verify(locationRepository).deleteById(location.getId());
		verify(logger).info("location deleted successfully");
	}

	@Test
	void deleteLocation_WithNonExistingLocation_ThrowsLocationException() {
		Location location =new Location("1", "place1","state1","country1");
		
		when(locationRepository.existsById(location.getId())).thenReturn(false);

		// Act & Assert
		assertThrows(LocationException.class, () -> locationService.deleteLocation(location));
		verify(logger).error("Error: Location doesn't exist");
	}
}
