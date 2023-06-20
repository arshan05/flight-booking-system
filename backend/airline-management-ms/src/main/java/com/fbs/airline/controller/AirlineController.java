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

import com.fbs.airline.exception.AirlineException;
import com.fbs.airline.model.Airline;
import com.fbs.airline.service.AirlineService;
import com.fbs.airline.service.AuthService;

@RestController
@RequestMapping("/api/airline")
public class AirlineController {

	private static final String YOU_ARE_UNAUTHORIZED = "You are Unauthorized!...";

	@Autowired
	AirlineService airlineService;

	@Autowired
	AuthService authService;

	// Airlines

	@PostMapping("/addAirline")
	public ResponseEntity<Airline> addAirline(@RequestHeader("cookie") String cookie, @RequestBody Airline airline)
			throws AirlineException {
		
		try {
			
			if (authService.isSessionValid(cookie)) {
				
				Airline newlyAddedAirline = airlineService.addAirline(airline);
				return ResponseEntity.ok(newlyAddedAirline);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		}

	}

	@GetMapping("/getAllAirlines")
	public ResponseEntity<List<Airline>> getAllAirlines(@RequestHeader("cookie") String cookie)
			throws AirlineException {
		try {
			if (authService.isSessionValid(cookie)) {
				return ResponseEntity.ok(airlineService.getAllAirlines());
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		}

	}

	@DeleteMapping("/deleteAirline")
	public ResponseEntity<String> deleteAirine(@RequestHeader("cookie") String cookie, @RequestBody Airline airline)
			throws AirlineException {
		try {
			if (authService.isSessionValid(cookie)) {
				airlineService.deleteAirline(airline);
				return ResponseEntity.ok("Successfully Deleted");
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		}

	}

	@PutMapping("/updateAirline")
	public ResponseEntity<Airline> updateAirline(@RequestHeader("cookie") String cookie, @RequestBody Airline airline)
			throws AirlineException {
		try {
			if (authService.isSessionValid(cookie)) {
				return ResponseEntity.ok(airlineService.updateAirline(airline));
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		}

	}

}
