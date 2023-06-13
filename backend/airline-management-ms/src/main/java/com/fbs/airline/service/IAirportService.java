package com.fbs.airline.service;

import java.util.List;

import com.fbs.airline.exception.AirportException;
import com.fbs.airline.model.Airport;

public interface IAirportService {
	Airport addAirport(Airport airport) throws AirportException;
	List<Airport> getAllAirports() throws AirportException;
	Airport updateAirport(Airport airport) throws AirportException;
	boolean deleteAirport(Airport airport) throws AirportException;
	
}
