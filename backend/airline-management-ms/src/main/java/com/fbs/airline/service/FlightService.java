package com.fbs.airline.service;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public Flight addFlight(Flight flight) throws FlightException {
		if (flightRepository.existsByFlightNumber(flight.getFlightNumber())) {
			throw new FlightException("Error: Flight by this number already exists");
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
			
			return flight;
		}
	}

	@Override
	public List<Flight> getAllFlights() throws FlightException {
		List<Flight> flights = flightRepository.findAll();

		if (flights.size() == 0) {
			throw new FlightException("Error: No Flights Found");
		} else {
			return flights;
		}
	}

	@Override
	public Flight updateFlight(Flight flight) throws FlightException {
		if (!flightRepository.existsById(flight.getId())) {
			throw new FlightException("Error: Flight doesn't exist");
		} else {
			return flightRepository.save(flight);
		}
	}

	@Override
	public boolean deleteFlight(Flight flight) throws FlightException {
		if (!flightRepository.existsById(flight.getId())) {
			throw new FlightException("Error: Flight doesn't exist");
		} else {
			flightRepository.deleteById(flight.getId());
			return !flightRepository.existsById(flight.getId());
		}
	}


}
