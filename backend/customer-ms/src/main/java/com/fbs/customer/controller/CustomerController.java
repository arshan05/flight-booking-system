package com.fbs.customer.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fbs.customer.model.FlightSeat;
import com.fbs.customer.model.Passenger;
import com.fbs.customer.model.Schedule;
import com.fbs.customer.request.BookFlightRequest;
import com.fbs.customer.request.FlightDetailsRequest;
import com.fbs.customer.service.AuthService;
import com.fbs.customer.service.FlightBookingService;

@RestController
@RequestMapping("/api/consumer")
public class CustomerController {

	@Autowired
	FlightBookingService flightBookingService;

	@Autowired
	AuthService authService;

	@GetMapping("/getFlights")
	public List<Schedule> getFlightDetails(@RequestHeader("cookie") String cookie, @RequestBody FlightDetailsRequest detailsRequest) {

		try {
			if (authService.isSessionValid(cookie)) {
				String start = detailsRequest.getStart();
				String destination = detailsRequest.getDestination();
				Date date = detailsRequest.getDate();
				System.out.println(detailsRequest);
				return flightBookingService.getFlightDetails(start, destination, date);

			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}

	}


	@GetMapping("/getAvailableSeats")
	public List<FlightSeat> getAvailableSeats(@RequestHeader("cookie") String cookie, @RequestBody Schedule schedule) {
		try {
			if (authService.isSessionValid(cookie)) {
				return flightBookingService.getAvailableSeats(schedule);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
		
	}

	@PostMapping("/bookFlight")
	public void bookFlight(@RequestHeader("cookie") String cookie, @RequestBody BookFlightRequest bookFlightRequest) {
		try {
			if (authService.isSessionValid(cookie)) {
				Schedule schedule = bookFlightRequest.getSchedule();
				Passenger passenger = bookFlightRequest.getPassenger();
				String seatNumber = bookFlightRequest.getSeatNumber();
				flightBookingService.bookFlight(schedule, passenger, seatNumber);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}

	}
}
