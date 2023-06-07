package com.spring.fbs.service;

import java.util.Date;
import java.util.List;

import com.spring.fbs.model.BookingDetails;
import com.spring.fbs.model.Flight;
import com.spring.fbs.model.User;

public interface FlightBookingService {
	List<Flight> getFlightDetails(String start, String destination, Date date);
	void bookFlight(Flight flight, User user);
	BookingDetails getBookingDetails(String pnr, String email);
}
