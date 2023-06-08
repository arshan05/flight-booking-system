package com.fbs.customer.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fbs.customer.model.Airline;
import com.fbs.customer.model.Airport;
import com.fbs.customer.model.Flight;
import com.fbs.customer.model.Schedule;
import com.fbs.customer.repository.AirlineRepository;
import com.fbs.customer.repository.AirportRepository;
import com.fbs.customer.repository.FlightRepository;
import com.fbs.customer.repository.PassengerRepository;
import com.fbs.customer.repository.ScheduleRepository;
import com.fbs.customer.request.FlightDetailsRequest;
import com.fbs.customer.service.FlightBookingService;

@RestController
public class CustomerController {

	@Autowired
	AirlineRepository airlineRepository;

	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	AirportRepository airportRepository;
	
	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	FlightBookingService flightBookingService;
	
	@GetMapping("/getFlights")
	public List<Schedule> getFlightDetails(@RequestBody FlightDetailsRequest detailsRequest) {
		String start = detailsRequest.getStart();
		String destination = detailsRequest.getDestination();
		Date date = detailsRequest.getDate();
		System.out.println(detailsRequest);
		return flightBookingService.getFlightDetails(start, destination, date);
	}
	

	@GetMapping("/getAll")
	public List<Schedule> getAll() {
		return scheduleRepository.findAll();
	}
	
	
	
}
