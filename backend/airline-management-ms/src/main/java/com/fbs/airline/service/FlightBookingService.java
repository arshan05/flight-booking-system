package com.fbs.airline.service;

import java.util.Date;
import java.util.List;

import com.fbs.airline.model.BookingDetails;
import com.fbs.airline.model.Flight;
import com.fbs.airline.model.Passenger;

public class FlightBookingService implements IFlightBookingService{

	@Override
	public List<Flight> getFlightDetails(String start, String destination, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bookFlight(Flight flight, Passenger user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BookingDetails getBookingDetails(String pnr, String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
