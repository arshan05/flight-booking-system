package com.fbs.airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fbs.airline.exception.LocationException;
import com.fbs.airline.model.Location;
import com.fbs.airline.service.AuthService;
import com.fbs.airline.service.ILocationService;

@RestController
@RequestMapping("/api/location")
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
public class LocationController {

	private static final String YOU_ARE_UNAUTHORIZED = "You are Unauthorized!...";

	@Autowired
	ILocationService locationService;

	@Autowired
	AuthService authService;

	// Locations

	@PostMapping("/addLocation")
	public ResponseEntity<Location> addLocation(@RequestHeader("cookie") String cookie, @RequestBody Location location)
			throws LocationException {
		try {
			if (authService.isSessionValid(cookie)) {
				Location newlyAddedLocation = locationService.addLocation(location);
				return ResponseEntity.ok(newlyAddedLocation);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		}

	}

	@GetMapping("/getAllLocations")
	public ResponseEntity<List<Location>> getAllLocations()
			throws LocationException {
		return ResponseEntity.ok(locationService.getAllLocations());

	}

	@DeleteMapping("/deleteLocation")
	public ResponseEntity<String> deleteAirine(@RequestHeader("cookie") String cookie, @RequestBody Location location)
			throws LocationException {
		try {
			if (authService.isSessionValid(cookie)) {
				locationService.deleteLocation(location);
				return ResponseEntity.ok("Successfully Deleted");
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		}

	}

	@PutMapping("/updateLocation")
	public ResponseEntity<Location> updateLocation(@RequestHeader("cookie") String cookie, @RequestBody Location location)
			throws LocationException {
		try {
			if (authService.isSessionValid(cookie)) {
				return ResponseEntity.ok(locationService.updateLocation(location));
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		}

	}

}
