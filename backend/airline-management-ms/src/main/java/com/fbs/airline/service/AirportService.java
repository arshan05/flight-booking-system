package com.fbs.airline.service;

import java.util.List;

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

	@Override
	public Airport addAirport(Airport airport) throws AirportException {
		if (airportRepository.existsByAirportName(airport.getAirportName())) {
			throw new AirportException("Error: Airline by this name already exists");
		} else {
			return airportRepository.save(airport);
		}
	}

	@Override
	public List<Airport> getAllAirports() throws AirportException {
		List<Airport> airports = airportRepository.findAll();

		if (airports.size() == 0) {
			throw new AirportException("Error: No Airlines Found");
		} else {
			return airports;
		}
	}

	@Override
	public Airport updateAirport(Airport airport) throws AirportException {
		if (!airportRepository.existsById(airport.getId())) {
			throw new AirportException("Error: Airport doesn't exist");
		} else {
			return airportRepository.save(airport);
		}
	}

	@Override
	public boolean deleteAirport(Airport airport) throws AirportException {
		if (!airportRepository.existsById(airport.getId())) {
			throw new AirportException("Error: Airport doesn't exist");
		} else {
			airportRepository.deleteById(airport.getId());
			return !airportRepository.existsById(airport.getId());
		}
	}

}
