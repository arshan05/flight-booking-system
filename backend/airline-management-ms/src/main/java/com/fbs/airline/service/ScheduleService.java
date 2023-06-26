package com.fbs.airline.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbs.airline.exception.ScheduleException;
import com.fbs.airline.model.Flight;
import com.fbs.airline.model.Schedule;
import com.fbs.airline.proxy.FareProxy;
import com.fbs.airline.repository.AirlineRepository;
import com.fbs.airline.repository.AirportRepository;
import com.fbs.airline.repository.FlightRepository;
import com.fbs.airline.repository.PassengerRepository;
import com.fbs.airline.repository.ScheduleRepository;

@Service
public class ScheduleService implements IScheduleService {

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
	public Schedule addSchedule(Schedule schedule) throws ScheduleException {
		// TODO : here change it to exists between startTime and endTime of a flight
		Flight flight = flightRepository.findById(schedule.getFlight().getId()).orElse(null);

		if (isFlightScheduledBetween(flight, schedule.getStartTime(), schedule.getEndTime())) {
			String message = "Error: Flight is already scheduled during this period";
			logger.error(message);
			throw new ScheduleException(message);
		} else {
			schedule = fareProxy.setFareForEachSeat(schedule);
			scheduleRepository.save(schedule);

			if (flight != null) {
				List<Schedule> schedulesinFlight = flight.getSchedules();
				if (schedulesinFlight == null) {
					List<Schedule> schedules = new ArrayList<>();
					schedules.add(schedule);
					flight.setSchedules(schedules);
					flightRepository.save(flight);
				} else {
					flight.getSchedules().add(schedule);
					flightRepository.save(flight);
				}
			}

			flight.getSchedules().add(schedule);
			flightRepository.save(flight);
			logger.info("schedule added successfully");
			return schedule;
		}
	}

	@Override
	public List<Schedule> getAllSchedules() throws ScheduleException {
		List<Schedule> schedules = scheduleRepository.findAll();

		if (schedules.size() == 0) {
			String message = "Error: No Schedules Found";
			logger.error(message);
			throw new ScheduleException(message);
		} else {
			logger.info("schedules retreived successfully. {} items found", schedules.size());
			return schedules;
		}
	}

	@Override
	public Schedule updateSchedule(Schedule schedule) throws ScheduleException {
		if (!scheduleRepository.existsById(schedule.getId())) {
			String message = "Error: Schedule doesn't exist";
			logger.error(message);
			throw new ScheduleException(message);
		} else {

			Schedule updatedSchedule = scheduleRepository.save(schedule);
			logger.info("schdedule updated successfully");
			return updatedSchedule;
		}
	}

	@Override
	public boolean deleteSchedule(Schedule schedule) throws ScheduleException {
		if (!scheduleRepository.existsById(schedule.getId())) {
			String message = "Error: Schedule doesn't exist";
			logger.error(message);
			throw new ScheduleException(message);
		} else {
			scheduleRepository.deleteById(schedule.getId());
			logger.info("schdedule deleted successfully");
			return !scheduleRepository.existsById(schedule.getId());
		}
	}

	public boolean isFlightScheduledBetween(Flight flight, Date startTime, Date endTime) {
		for (Schedule schedule : flight.getSchedules()) {
			Date scheduleStartTime = schedule.getStartTime();
			Date scheduleEndTime = schedule.getEndTime();

			if (scheduleStartTime.equals(startTime) || scheduleEndTime.equals(endTime)) {
				return true;
			}

			if (scheduleStartTime.after(startTime) && scheduleEndTime.before(endTime)) {
				return true;
			}

			if (scheduleStartTime.before(startTime) && scheduleEndTime.after(startTime)) {
				return true;
			}

			if (scheduleStartTime.before(endTime) && scheduleEndTime.after(endTime)) {
				return true;
			}
		}

		return false;
	}

}
