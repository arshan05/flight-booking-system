package com.fbs.customer.service;

import java.util.Date;
import java.util.List;

import com.fbs.customer.model.BookingDetails;
import com.fbs.customer.model.Passenger;
import com.fbs.customer.model.Schedule;

public interface IFlightBookingService {
	List<Schedule> getFlightDetails(String start, String destination, Date date);
	BookingDetails bookFlight(Schedule schedule, Passenger user,String seatNumber);
	BookingDetails getBookingDetails(String pnr, String email);
	void cancelTicket(BookingDetails bookingDetails);
}
