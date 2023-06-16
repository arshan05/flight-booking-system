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

import com.fbs.airline.exception.AirportException;
import com.fbs.airline.model.Airport;
import com.fbs.airline.service.AirportService;
import com.fbs.airline.service.AuthService;

@RestController
@RequestMapping("/api/airport")
public class AirportController {

	@Autowired
	AirportService AirportService;

	@Autowired
	AuthService authService;

	// Airports

	@PostMapping("/addAirport")
	public ResponseEntity<Airport> addAirport(@RequestHeader("cookie") String cookie, @RequestBody Airport airport)
			throws AirportException {
		try {
			if (authService.isSessionValid(cookie)) {
				Airport newlyAddedAirport = AirportService.addAirport(airport);
				return ResponseEntity.ok(newlyAddedAirport);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}

	}

	@GetMapping("/getAllAirports")
	public ResponseEntity<List<Airport>> getAllAirports(@RequestHeader("cookie") String cookie)
			throws AirportException {
		try {
			if (authService.isSessionValid(cookie)) {
				return ResponseEntity.ok(AirportService.getAllAirports());
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}

	}

	@DeleteMapping("/deleteAirport")
	public ResponseEntity<String> deleteAirine(@RequestHeader("cookie") String cookie, @RequestBody Airport airport)
			throws AirportException {
		try {
			if (authService.isSessionValid(cookie)) {
				AirportService.deleteAirport(airport);
				return ResponseEntity.ok("Successfully Deleted");
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}

	}

	@PutMapping("/updateAirport")
	public ResponseEntity<Airport> updateAirport(@RequestHeader("cookie") String cookie, @RequestBody Airport airport)
			throws AirportException {
		try {
			if (authService.isSessionValid(cookie)) {
				return ResponseEntity.ok(AirportService.updateAirport(airport));
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}

	}

}
