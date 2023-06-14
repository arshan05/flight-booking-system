package com.fbs.airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fbs.airline.exception.AirlineException;
import com.fbs.airline.model.Airline;
import com.fbs.airline.service.AirlineService;

@RestController
@RequestMapping("/api/airline")
public class AirlineController {

	@Autowired
	AirlineService airlineService;

	// Airlines

	@PostMapping("/addAirline")
	public ResponseEntity<Airline> addAirline(@RequestBody Airline airline) throws AirlineException {
		Airline newlyAddedAirline = airlineService.addAirline(airline);
		return ResponseEntity.ok(newlyAddedAirline);

	}

	@GetMapping("/getAllAirlines")
	public ResponseEntity<List<Airline>> getAllAirlines() throws AirlineException {
		return ResponseEntity.ok(airlineService.getAllAirlines());
	}

	@DeleteMapping("/deleteAirline")
	public ResponseEntity<String> deleteAirine(@RequestBody Airline airline) throws AirlineException {
		airlineService.deleteAirline(airline);
		return ResponseEntity.ok("Successfully Deleted");
	}

	@PutMapping("/updateAirline")
	public ResponseEntity<Airline>  updateAirline(@RequestBody Airline airline) throws AirlineException {
		return ResponseEntity.ok(airlineService.updateAirline(airline));
	}

}
