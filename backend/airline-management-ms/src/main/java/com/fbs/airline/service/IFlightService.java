package com.fbs.airline.service;

import java.util.List;

import com.fbs.airline.exception.FlightException;
import com.fbs.airline.model.Flight;

public interface IFlightService {
	
	Flight addFlight(Flight flight) throws FlightException;
	List<Flight> getAllFlights() throws FlightException;
	Flight updateFlight(Flight flight) throws FlightException;
	boolean deleteFlight(Flight flight) throws FlightException;	
}
