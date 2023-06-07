package com.spring.fbs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.fbs.model.Airline;
import com.spring.fbs.model.Flight;
import com.spring.fbs.repository.AirlineRepository;
import com.spring.fbs.repository.FlightRepository;

@RestController
public class FBSController {
	
	@Autowired
	AirlineRepository airlineRepository;
	
	@Autowired
	FlightRepository flightRepository;
	
	@PostMapping("/addAirline")
	public void addAirline(@RequestBody Airline airline) {
		airlineRepository.save(airline);
	}
	
	@PostMapping("/addFlight")
	public void addFlight(@RequestBody Flight flight) {
		String id = flight.getAirlineCompany().getId();
		Optional<Airline> airline = airlineRepository.findById(id);
//		System.out.println(airline.get());
		if (airline.get() != null){
			List<Flight> flights = new ArrayList<>();
			if (airline.get().getFlights() != null) {
				flights = airline.get().getFlights();
				
			}
			flights.add(flight);
			
			airline.get().setFlights(flights);
			airlineRepository.save(airline.get());
			System.out.println(airline.get());
			
		}
		flightRepository.save(flight);
	}
}
