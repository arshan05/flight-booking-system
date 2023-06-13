package com.fbs.airline.service;

import java.util.List;

import com.fbs.airline.exception.AirlineException;
import com.fbs.airline.model.Airline;

public interface IAirlineService {
	List<Airline> getAllAirlines() throws AirlineException;
	Airline addAirline(Airline airline) throws AirlineException;
	Airline updateAirline(Airline airline) throws AirlineException;
	boolean deleteAirline(Airline airline) throws AirlineException;
}
