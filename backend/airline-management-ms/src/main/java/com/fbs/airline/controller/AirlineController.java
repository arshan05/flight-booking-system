package com.fbs.airline.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fbs.airline.exception.AirlineException;
import com.fbs.airline.exception.AirportException;
import com.fbs.airline.model.Airline;
import com.fbs.airline.model.Airport;
import com.fbs.airline.model.Flight;
import com.fbs.airline.model.Passenger;
import com.fbs.airline.model.Schedule;
import com.fbs.airline.proxy.FareProxy;
import com.fbs.airline.repository.AirlineRepository;
import com.fbs.airline.repository.AirportRepository;
import com.fbs.airline.repository.FlightRepository;
import com.fbs.airline.repository.PassengerRepository;
import com.fbs.airline.repository.ScheduleRepository;
import com.fbs.airline.service.AirlineService;

@RestController
public class AirlineController {

	@Autowired
	AirlineService airlineService;

	@PostMapping("/addAirline")
	public void addAirline(@RequestBody Airline airline) throws AirlineException {
		airlineService.addAirline(airline);
	}

	@PostMapping("/addFlight")
	public void addFlight(@RequestBody Flight flight) {

		String id = flight.getAirlineCompany().getId();
		Airline airline = airlineRepository.findById(id).get();
		flight = fareProxy.setFlightSeat(flight);
		if (airline != null) {
			Flight fly = flightRepository.save(flight);
			List<String> flightsIds = new ArrayList<>();
			if (airline.getFlightsIds() != null) {
				for (String f : airline.getFlightsIds()) {
					flightsIds.add(f);
				}
			}
			flightsIds.add(fly.getId());

			airline.setFlightsIds(flightsIds);
			airlineRepository.save(airline);

		}

	}
	
	@PostMapping("/addAirport")
	public void addAirport(@RequestBody Airport airport) throws AirportException {
		airlineService.addAirport(airport);
	}
	
	@PostMapping("/addSchedule")
	public Schedule addSchedule(@RequestBody Schedule schedule) {
		schedule = fareProxy.setFareForEachSeat(schedule);
		scheduleRepository.save(schedule);
		return schedule;
	}
	
	@PostMapping("/addPassenger")
	public void addPassenger(@RequestBody Passenger passenger) {
		passengerRepository.save(passenger);
	}
	
	@GetMapping("/find/{id}")
	public Flight findflight(@PathVariable String id) {
		return flightRepository.findById(id).orElse(null);
	}
	
	
}
