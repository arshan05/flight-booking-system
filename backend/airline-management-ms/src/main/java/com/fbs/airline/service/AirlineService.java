package com.fbs.airline.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbs.airline.exception.AirlineException;
import com.fbs.airline.model.Airline;
import com.fbs.airline.proxy.FareProxy;
import com.fbs.airline.repository.AirlineRepository;
import com.fbs.airline.repository.AirportRepository;
import com.fbs.airline.repository.FlightRepository;
import com.fbs.airline.repository.PassengerRepository;
import com.fbs.airline.repository.ScheduleRepository;

@Service
public class AirlineService implements IAirlineService {

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

		
	Logger logger = LoggerFactory.getLogger(Airline.class);
	
	@Override
	public List<Airline> getAllAirlines() throws AirlineException {
		List<Airline> airlines = airlineRepository.findAll();

		if (airlines.isEmpty()) {
			throw new AirlineException("Error: No Airlines Found");
		} else {
			logger.info("Airlines retreived successfully " + airlines.size() + " items found");
			return airlines;
		}

	}

	@Override
	public Airline addAirline(Airline airline) throws AirlineException {
		if (airlineRepository.existsByAirlineName(airline.getAirlineName())) {
			throw new AirlineException("Error: Airline by this name already exists");
		} else {
			Airline addedAirline = airlineRepository.save(airline);
			logger.info("airline added successfully");
			return addedAirline;
		}

	}

	@Override
	public Airline updateAirline(Airline airline) throws AirlineException {
		if (!airlineRepository.existsById(airline.getId())) {
			throw new AirlineException("Error: Airline doesn't exist");
		} else {
			Airline updated = airlineRepository.save(airline);
			logger.info("airline updated successfully");
			return updated;
		}
	}

	@Override
	public boolean deleteAirline(Airline airline) throws AirlineException {
		if (!airlineRepository.existsById(airline.getId())) {
			throw new AirlineException("Error: Airline doesn't exist");
		} else {
			airlineRepository.deleteById(airline.getId());
			logger.info("airline deleted successfully");
			return !airlineRepository.existsById(airline.getId());
		}
	}
}
