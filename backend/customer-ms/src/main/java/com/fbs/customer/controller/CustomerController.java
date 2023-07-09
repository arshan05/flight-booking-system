package com.fbs.customer.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fbs.customer.model.BookingDetails;
import com.fbs.customer.model.FlightSeat;
import com.fbs.customer.model.GetBookingDetailsRequest;
import com.fbs.customer.model.Passenger;
import com.fbs.customer.model.Schedule;
import com.fbs.customer.request.BookFlightRequest;
import com.fbs.customer.service.AuthService;
import com.fbs.customer.service.IFlightBookingService;

@RestController
@RequestMapping("/api/consumer")
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
public class CustomerController {

	@Autowired
	IFlightBookingService flightBookingService;

	@Autowired
	AuthService authService;

	@GetMapping("/getFlights")
	public List<Schedule> getFlightDetails(@RequestParam String start, @RequestParam String destination,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

		return flightBookingService.getFlightDetails(start, destination, date);

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
	public ResponseEntity<BookingDetails> bookFlight(@RequestHeader("cookie") String cookie,
			@RequestBody BookFlightRequest bookFlightRequest) {
		try {
			if (authService.isSessionValid(cookie)) {
				Schedule schedule = bookFlightRequest.getSchedule();
				Passenger passenger = bookFlightRequest.getPassenger();
				String seatNumber = bookFlightRequest.getSeatNumber();
				BookingDetails bookingDetails = flightBookingService.bookFlight(schedule, passenger, seatNumber);
				return ResponseEntity.ok(bookingDetails);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}

	}

	@PostMapping("/addPassenger")
	public ResponseEntity<Passenger> addPassenger(@RequestHeader("cookie") String cookie,
			@RequestBody Passenger passenger) {

		try {

			if (authService.isSessionValid(cookie)) {
				System.out.println("o=========k");
				Passenger newlyAddedPassenger = flightBookingService.addPassenger(passenger);
				return ResponseEntity.ok(newlyAddedPassenger);
			} else {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "YOU_ARE_UNAUTHORIZED");
			}
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "YOU_ARE_UNAUTHORIZED");
		}

	}

	
	@GetMapping("/getBookingDetails")
	public List<BookingDetails> getBookingDetails(@RequestHeader("cookie") String cookie, @RequestBody GetBookingDetailsRequest request) {
		try {
			if (authService.isSessionValid(cookie)) {
				return flightBookingService.getBookingDetailsByPnrAndEmail(request.getPnr(),request.getEmail());
			} else {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "YOU_ARE_UNAUTHORIZED");
			}
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "YOU_ARE_UNAUTHORIZED");
		}

	}
	
	@GetMapping("/getBookingDetailsByEmail/{email}")
	public List<BookingDetails> getBookingDetails(@RequestHeader("cookie") String cookie, @PathVariable String email) {
		try {
			if (authService.isSessionValid(cookie)) {
				return flightBookingService.getBookingDetailsByEmail(email);
			} else {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "YOU_ARE_UNAUTHORIZED");
			}
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "YOU_ARE_UNAUTHORIZED");
		}

	}
	
	@PostMapping("/cancelTicket")
	public ResponseEntity<String> cancelTicket(@RequestHeader("cookie") String cookie, @RequestBody BookingDetails bookingDetails) {
		try {
			if (authService.isSessionValid(cookie)) {
				flightBookingService.cancelTicket(bookingDetails);
				return ResponseEntity.ok("Ticket canceled");
			} else {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "YOU_ARE_UNAUTHORIZED");
			}
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "YOU_ARE_UNAUTHORIZED");
		}

	}
	
	public CustomerController() {
		// TODO Auto-generated constructor stub
		System.out.println("hello");
	}
	
	
	
	@PostMapping("/hello")
	public String hello(@RequestBody String name) {
		return "hello "+ name;
	}
}
