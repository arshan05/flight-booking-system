package com.fbs.airline.service;

import java.util.List;

import com.fbs.airline.exception.AirlineException;
import com.fbs.airline.exception.AirportException;
import com.fbs.airline.exception.FlightException;
import com.fbs.airline.exception.ScheduleException;
import com.fbs.airline.model.Airline;
import com.fbs.airline.model.Airport;
import com.fbs.airline.model.Flight;
import com.fbs.airline.model.Schedule;

public interface IAirlineService {
	List<Airline> getAllAirlines() throws AirlineException;
	Airline addAirline(Airline airline) throws AirlineException;
	Airline updateAirline(Airline airline) throws AirlineException;
	boolean deleteAirline(Airline airline) throws AirlineException;

	Flight addFlight(Flight flight) throws FlightException;
	List<Flight> getAllFlights() throws FlightException;
	Flight updateFlight(Flight flight) throws FlightException;
	boolean deleteFlight(Flight flight) throws FlightException;

	Airport addAirport(Airport airport) throws AirportException;
	List<Airport> getAllAirports() throws AirportException;
	Airport updateAirport(Airport airport) throws AirportException;
	boolean deleteAirport(Airport airport) throws AirportException;
	
	Schedule addSchedule(Schedule schedule) throws ScheduleException;
	List<Schedule> getAllSchedules() throws ScheduleException;
	Schedule updateSchedule(Schedule schedule) throws ScheduleException;
	boolean deleteSchedule(Schedule schedule) throws ScheduleException;
	
	
	
}
