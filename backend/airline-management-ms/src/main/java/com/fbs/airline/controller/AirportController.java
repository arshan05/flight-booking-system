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

import com.fbs.airline.exception.AirportException;
import com.fbs.airline.model.Airport;
import com.fbs.airline.service.AirportService;

@RestController
@RequestMapping("/api/airport")
public class AirportController {

	@Autowired
	AirportService airportService;
	
	//Airport
	
	@PostMapping("/addAirport")
	public void addAirport(@RequestBody Airport airport) throws AirportException {
		airportService.addAirport(airport);
	}
	
	@GetMapping("/getAllAirports")
	public List<Airport> getAllAirports() throws AirportException{
		return airportService.getAllAirports();
	}

	@DeleteMapping("/deleteAirport")
	public boolean deleteAirport(@RequestBody Airport airport) throws AirportException {
		return airportService.deleteAirport(airport);
	}
	
	@PutMapping("/updateAirport")
	public Airport updateAirport(@RequestBody Airport airport) throws AirportException {
		return airportService.updateAirport(airport);
	}
	
	
}
