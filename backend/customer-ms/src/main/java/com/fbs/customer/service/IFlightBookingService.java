package com.fbs.customer.service;

import java.util.Date;
import java.util.List;

import com.fbs.customer.model.BookingDetails;
import com.fbs.customer.model.Flight;
import com.fbs.customer.model.Passenger;
import com.fbs.customer.model.Schedule;

public interface IFlightBookingService {
	List<Schedule> getFlightDetails(String start, String destination, Date date);
	void bookFlight(Flight flight, Passenger user);
	BookingDetails getBookingDetails(String pnr, String email);
}
