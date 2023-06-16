package com.fbs.airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.fbs.airline.service.FlightService;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

	@Autowired
	FlightService FlightService;

	@Autowired
	AuthService authService;

	// Flights

	@PostMapping("/addFlight")
	public ResponseEntity<Flight> addFlight(@RequestHeader("cookie") String cookie, @RequestBody Flight flight)
			throws FlightException {
		try {
			if (authService.isSessionValid(cookie)) {
				Flight newlyAddedFlight = FlightService.addFlight(flight);
				return ResponseEntity.ok(newlyAddedFlight);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}

	}

	@GetMapping("/getAllFlights")
	public ResponseEntity<List<Flight>> getAllFlights(@RequestHeader("cookie") String cookie)
			throws FlightException {
		try {
			if (authService.isSessionValid(cookie)) {
				return ResponseEntity.ok(FlightService.getAllFlights());
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}

	}

	@DeleteMapping("/deleteFlight")
	public ResponseEntity<String> deleteAirine(@RequestHeader("cookie") String cookie, @RequestBody Flight flight)
			throws FlightException {
		try {
			if (authService.isSessionValid(cookie)) {
				FlightService.deleteFlight(flight);
				return ResponseEntity.ok("Successfully Deleted");
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}

	}

	@PutMapping("/updateFlight")
	public ResponseEntity<Flight> updateFlight(@RequestHeader("cookie") String cookie, @RequestBody Flight flight)
			throws FlightException {
		try {
			if (authService.isSessionValid(cookie)) {
				return ResponseEntity.ok(FlightService.updateFlight(flight));
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}

	}

}
