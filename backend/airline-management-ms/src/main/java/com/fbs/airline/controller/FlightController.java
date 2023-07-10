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

import com.fbs.airline.exception.FlightException;
import com.fbs.airline.model.Flight;
import com.fbs.airline.service.AuthService;
import com.fbs.airline.service.IFlightService;

@RestController
@RequestMapping("/api/flight")
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
public class FlightController {

	private static final String YOU_ARE_UNAUTHORIZED = "You are Unauthorized!...";

	@Autowired
	IFlightService flightService;

	@Autowired
	AuthService authService;

	// Flights

	@PostMapping("/addFlight")
	public ResponseEntity<Flight> addFlight(@RequestHeader("cookie") String cookie, @RequestBody Flight flight)
			throws FlightException {
		try {
			if (authService.isSessionValid(cookie)) {
				Flight newlyAddedFlight = flightService.addFlight(flight);
				return ResponseEntity.ok(newlyAddedFlight);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		}

	}

	@GetMapping("/getAllFlights")
	public ResponseEntity<List<Flight>> getAllFlights(@RequestHeader("cookie") String cookie)
			throws FlightException {
		try {
			if (authService.isSessionValid(cookie)) {
				return ResponseEntity.ok(flightService.getAllFlights());
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		}

	}

	@DeleteMapping("/deleteFlight")
	public ResponseEntity<String> deleteAirine(@RequestHeader("cookie") String cookie, @RequestBody Flight flight)
			throws FlightException {
		try {
			if (authService.isSessionValid(cookie)) {
				flightService.deleteFlight(flight);
				return ResponseEntity.ok("Successfully Deleted");
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		}

	}

	@PutMapping("/updateFlight")
	public ResponseEntity<Flight> updateFlight(@RequestHeader("cookie") String cookie, @RequestBody Flight flight)
			throws FlightException {
		try {
			if (authService.isSessionValid(cookie)) {
				return ResponseEntity.ok(flightService.updateFlight(flight));
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		}

	}

}
