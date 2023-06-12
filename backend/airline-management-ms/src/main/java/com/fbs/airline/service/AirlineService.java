package com.fbs.airline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbs.airline.exception.AirlineException;
import com.fbs.airline.exception.AirportException;
import com.fbs.airline.exception.FlightException;
import com.fbs.airline.exception.ScheduleException;
import com.fbs.airline.model.Airline;
import com.fbs.airline.model.Airport;
import com.fbs.airline.model.Flight;
import com.fbs.airline.model.Schedule;
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

	@Override
	public List<Airline> getAllAirlines() throws AirlineException {
		List<Airline> airlines = airlineRepository.findAll();

		if (airlines.size() == 0) {
			throw new AirlineException("Error: No Airlines Found");
		} else {
			return airlines;
		}

	}

	@Override
	public Airline addAirline(Airline airline) throws AirlineException {
		if (airlineRepository.existsByAirlineName(airline.getAirlineName())) {
			throw new AirlineException("Error: Airline by this name already exists");
		} else {
			return airlineRepository.save(airline);
		}

	}

	@Override
	public Airline updateAirline(Airline airline) throws AirlineException {
		if (!airlineRepository.existsById(airline.getId())) {
			throw new AirlineException("Error: Airline doesn't exist");
		} else {
			return airlineRepository.save(airline);
		}
	}

	@Override
	public boolean deleteAirline(Airline airline) throws AirlineException {
		if (!airlineRepository.existsById(airline.getId())) {
			throw new AirlineException("Error: Airline doesn't exist");
		} else {
			airlineRepository.deleteById(airline.getId());
			return !airlineRepository.existsById(airline.getId());
		}
	}

	@Override
	public Flight addFlight(Flight flight) throws FlightException {
		if (flightRepository.existsByFlightNumber(flight.getFlightNumber())) {
			throw new FlightException("Error: Flight by this number already exists");
		} else {
			return flightRepository.save(flight);
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

	@Override
	public Schedule addSchedule(Schedule schedule) throws ScheduleException {
		// TODO : here change it to exists between startTime and endTime
		if (scheduleRepository.existsById(schedule.getId())) {
			throw new ScheduleException("Error: Flight is already scheduled during this period");
		} else {
			return scheduleRepository.save(schedule);
		}
	}

	@Override
	public List<Schedule> getAllSchedules() throws ScheduleException {
		List<Schedule> schedules= scheduleRepository.findAll();

		if (schedules.size() == 0) {
			throw new ScheduleException("Error: No Schedules Found");
		} else {
			return schedules;
		}
	}

	@Override
	public Schedule updateSchedule(Schedule schedule) throws ScheduleException {
		if (!scheduleRepository.existsById(schedule.getId())) {
			throw new ScheduleException("Error: Schedule doesn't exist");
		} else {
			return scheduleRepository.save(schedule);
		}
	}

	@Override
	public boolean deleteSchedule(Schedule schedule) throws ScheduleException {
		if (!scheduleRepository.existsById(schedule.getId())) {
			throw new ScheduleException("Error: Schedule doesn't exist");
		} else {
			scheduleRepository.deleteById(schedule.getId());
			return !scheduleRepository.existsById(schedule.getId());
		}
	}

}
