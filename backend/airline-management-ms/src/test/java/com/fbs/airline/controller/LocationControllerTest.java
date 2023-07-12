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

import com.fbs.airline.exception.LocationException;
import com.fbs.airline.model.Location;
import com.fbs.airline.service.AuthService;
import com.fbs.airline.service.ILocationService;

class LocationControllerTest {


	@Mock
	ILocationService locationService;

	@Mock
	AuthService authService;

	@InjectMocks
	LocationController locationController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
    void testAddLocation() throws LocationException {
        // Mock the AuthService to return true for session validity
        when(authService.isSessionValid(anyString())).thenReturn(true);

        Location location = new Location();
        when(locationService.addLocation(any(Location.class))).thenReturn(location);

        ResponseEntity<Location> response = locationController.addLocation("dummy-cookie", location);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(location, response.getBody());

    }

	@Test
    void testGetAllLocations() throws LocationException {
        when(authService.isSessionValid(anyString())).thenReturn(true);

        List<Location> locations = new ArrayList<>();
        when(locationService.getAllLocations()).thenReturn(locations);

        ResponseEntity<List<Location>> response = locationController.getAllLocations();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(locations, response.getBody());

    }

	@Test
	public void testDeleteLocation() throws LocationException {
		Location location = new Location();

		when(locationService.deleteLocation(location)).thenReturn(true);
		when(authService.isSessionValid(anyString())).thenReturn(true);

		ResponseEntity<String> response = locationController.deleteAirine("dummy-cookie", location);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Successfully Deleted", response.getBody());
	}

	@Test
	public void testUpdateLocation() throws LocationException {
		Location location = new Location();

		when(locationService.updateLocation(location)).thenReturn(location);
		when(authService.isSessionValid(anyString())).thenReturn(true);

		ResponseEntity<Location> response = locationController.updateLocation("dummy-cookie", location);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(location, response.getBody());
	}

	@Test
    void testAddLocationThrowsUnauthorizedException() throws LocationException {
        when(authService.isSessionValid(anyString())).thenReturn(false);

        Location location = new Location();

        assertThrows(ResponseStatusException.class, () -> {
            locationController.addLocation("dummy-cookie", location);
        });

    }

//	@Test
//    void testGetAllLocationsThrowsUnauthorizedException() throws LocationException {
//        when(authService.isSessionValid(anyString())).thenReturn(false);
//        
//        assertThrows(ResponseStatusException.class, () -> {
//            locationController.getAllLocations();
//        });
//
//    }

	@Test
	public void testDeleteLocationThrowsUnauthorizedException() throws LocationException {
		Location location = new Location();

		when(authService.isSessionValid(anyString())).thenReturn(false);
		assertThrows(ResponseStatusException.class, () -> {
			locationController.deleteAirine("dummy-cookie", location);
		});
	}

	@Test
	public void testUpdateLocationThrowsUnauthorizedException() throws LocationException {
		Location location = new Location();

		when(authService.isSessionValid(anyString())).thenReturn(false);
		assertThrows(ResponseStatusException.class, () -> {
			locationController.updateLocation("dummy-cookie", location);
		});
	}


}
