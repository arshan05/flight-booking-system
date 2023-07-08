package com.fbs.customer.service;

import java.util.Date;
import java.util.List;

import com.fbs.customer.model.BookingDetails;
import com.fbs.customer.model.FlightSeat;
import com.fbs.customer.model.Passenger;
import com.fbs.customer.model.Schedule;

public interface IFlightBookingService {
	List<Schedule> getFlightDetails(String start, String destination, Date date);
	BookingDetails bookFlight(Schedule schedule, Passenger user,String seatNumber);
	void cancelTicket(BookingDetails bookingDetails);
	List<FlightSeat> getAvailableSeats(Schedule schedule);
	Passenger addPassenger(Passenger passenger);
	List<BookingDetails> getBookingDetailsByEmail(String email);
	List<BookingDetails> getBookingDetailsByPnrAndEmail(String pnr, String email);
}
