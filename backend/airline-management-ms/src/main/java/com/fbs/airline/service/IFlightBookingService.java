package com.fbs.airline.service;

import java.util.Date;
import java.util.List;

import com.fbs.airline.model.BookingDetails;
import com.fbs.airline.model.Flight;
import com.fbs.airline.model.Passenger;

public interface IFlightBookingService {
	List<Flight> getFlightDetails(String start, String destination, Date date);
	void bookFlight(Flight flight, Passenger user);
	BookingDetails getBookingDetails(String pnr, String email);
}
