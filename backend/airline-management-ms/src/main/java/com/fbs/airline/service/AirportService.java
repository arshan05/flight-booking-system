package com.fbs.airline.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbs.airline.exception.AirportException;
import com.fbs.airline.model.Airport;
import com.fbs.airline.proxy.FareProxy;
import com.fbs.airline.repository.AirlineRepository;
import com.fbs.airline.repository.AirportRepository;
import com.fbs.airline.repository.FlightRepository;
import com.fbs.airline.repository.PassengerRepository;
import com.fbs.airline.repository.ScheduleRepository;

@Service
public class AirportService implements IAirportService {

	@Autowired
	AirlineRepository airlineRepository;

	@Autowired
	FlightRepository flightRepository;

	@Autowired
	AirportRepository airportRepository;

	@Autowired
	ScheduleRepository scheduleRepository;

	@Autowired
	PassengerRepository passengerRepository;

	@Autowired
	FareProxy fareProxy;

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public Airport addAirport(Airport airport) throws AirportException {
		if (airportRepository.existsByAirportName(airport.getAirportName())) {
			String message = "Error: Airline by this name already exists";
			logger.error(message);
			throw new AirportException(message);
		} else {
			Airport addedAirport = airportRepository.save(airport);
			logger.info("airport added successfully");
			return addedAirport;
		}
	}

	@Override
	public List<Airport> getAllAirports() throws AirportException {
		List<Airport> airports = airportRepository.findAll();

		if (airports.isEmpty()) {
			String message = "Error: No Airlines Found";
			logger.error(message);
			throw new AirportException(message);
		} else {
			logger.info("Airlines retrieved successfully. {} items found", airports.size());
			return airports;
		}
	}

	@Override
	public Airport updateAirport(Airport airport) throws AirportException {
		if (!airportRepository.existsById(airport.getId())) {
			String message = "Error: Airport doesn't exist";
			logger.error(message);
			throw new AirportException(message);
		} else {
			Airport updated = airportRepository.save(airport);
			logger.info("airport updated successfully");
			return updated;
		}
	}

	@Override
	public boolean deleteAirport(Airport airport) throws AirportException {
		if (!airportRepository.existsById(airport.getId())) {
			String message = "Error: Airport doesn't exist";
			logger.error(message);
			throw new AirportException(message);
		} else {
			airportRepository.deleteById(airport.getId());
			logger.info("airport deleted successfully");
			return !airportRepository.existsById(airport.getId());
		}
	}

}
