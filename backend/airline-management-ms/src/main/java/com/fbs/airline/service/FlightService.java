package com.fbs.airline.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbs.airline.exception.FlightException;
import com.fbs.airline.model.Airline;
import com.fbs.airline.model.Flight;
import com.fbs.airline.proxy.FareProxy;
import com.fbs.airline.repository.AirlineRepository;
import com.fbs.airline.repository.AirportRepository;
import com.fbs.airline.repository.FlightRepository;
import com.fbs.airline.repository.PassengerRepository;
import com.fbs.airline.repository.ScheduleRepository;

@Service
public class FlightService implements IFlightService {

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
	public Flight addFlight(Flight flight) throws FlightException {
		if (flightRepository.existsByFlightNumber(flight.getFlightNumber())) {
			String message = "Error: Flight by this number already exists";
			logger.error(message);
			throw new FlightException(message);
		} else {
			flight = fareProxy.setFlightSeat(flight);
			
			flightRepository.save(flight);
			Airline airline = airlineRepository.findById(flight.getAirlineCompany().getId()).orElse(null);
			if (airline!=null) {
				if(airline.getFlights() == null) {
					List<Flight> flights = new ArrayList<>();
					flights.add(flight);
					airline.setFlights(flights);
					airlineRepository.save(airline);
				}
				else {
					airline.getFlights().add(flight);
					airlineRepository.save(airline);
				}
			}
			logger.info("flight added successfully");
			return flight;
		}
	}

	@Override
	public List<Flight> getAllFlights() throws FlightException {
		List<Flight> flights = flightRepository.findAll();

		if (flights.isEmpty()) {
			String message = "Error: No Flights Found";
			logger.error(message);
			throw new FlightException(message);
		} else {
			logger.info("Flights retrieved successfully. {} items found", flights.size());

			return flights;
		}
	}

	@Override
	public Flight updateFlight(Flight flight) throws FlightException {
		if (!flightRepository.existsById(flight.getId())) {
			String message = "Error: Flight doesn't exist";
			logger.error(message);
			throw new FlightException(message);
		} else {
			Flight updated = flightRepository.save(flight);
			logger.info("flight updated successfully");
			return updated;
		}
	}

	@Override
	public boolean deleteFlight(Flight flight) throws FlightException {
		if (!flightRepository.existsById(flight.getId())) {
			String message = "Error: Flight doesn't exist";
			logger.error(message);
			throw new FlightException(message);
		} else {
			flightRepository.deleteById(flight.getId());
			logger.info("flight deleted successfully");
			return true;
		}
	}


}
