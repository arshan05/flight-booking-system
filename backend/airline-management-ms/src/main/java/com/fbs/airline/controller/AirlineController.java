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

import com.fbs.airline.exception.AirlineException;
import com.fbs.airline.model.Airline;
import com.fbs.airline.service.AirlineService;

@RestController
@RequestMapping("/api/airline")
public class AirlineController {

	@Autowired
	AirlineService airlineService;

	//Airlines
	
	@PostMapping("/addAirline")
	public void addAirline(@RequestBody Airline airline) throws AirlineException {		
		airlineService.addAirline(airline);
	}
	
	@GetMapping("/getAllAirlines")
	public List<Airline> getAllAirlines() throws AirlineException{
		return airlineService.getAllAirlines();
	}

	@DeleteMapping("/deleteAirline")
	public boolean deleteAirine(@RequestBody Airline airline) throws AirlineException {
		return airlineService.deleteAirline(airline);
	}
	
	@PutMapping("/updateAirline")
	public Airline updateAirline(@RequestBody Airline airline) throws AirlineException {
		return airlineService.updateAirline(airline);
	}
	
}
