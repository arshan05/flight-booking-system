package com.fbs.airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fbs.airline.exception.FlightException;
import com.fbs.airline.model.Flight;
import com.fbs.airline.service.FlightService;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

	@Autowired
	FlightService flightService;

	//flights
	
	@PostMapping("/addFlight")
	public Flight addFlight(@RequestBody Flight flight) throws FlightException {
		return flightService.addFlight(flight);
	}
	
	@GetMapping("/getAllFlights")
	public List<Flight> getAllFlights() throws FlightException{
		return flightService.getAllFlights();
	}

	@DeleteMapping("/deleteFlight")
	public boolean deleteFlight(@RequestBody Flight flight) throws FlightException {
		return flightService.deleteFlight(flight);
	}
	
	@PutMapping("/updateFLight")
	public Flight updateFlight(@RequestBody Flight flight) throws FlightException {
		return flightService.updateFlight(flight);
	}

}
