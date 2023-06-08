package com.fbs.customer.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbs.customer.model.BookingDetails;
import com.fbs.customer.model.Flight;
import com.fbs.customer.model.Passenger;
import com.fbs.customer.model.Schedule;
import com.fbs.customer.repository.CustomScheduleRepository;
import com.fbs.customer.repository.ScheduleRepository;


@Service
public class FlightBookingService implements IFlightBookingService{
	
	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Autowired
	CustomScheduleRepository customScheduleRepository;
	
	@Override
	public List<Schedule> getFlightDetails(String start, String destination, Date date) {
		// TODO Auto-generated method stub
//		List<Schedule> availableSchedules = scheduleRepository.findByPlaceAndPlaceAndStartTimeGreaterThanEqual(start,destination,date);
//		return availableSchedules;
		
		List<Schedule> findByBoarding = customScheduleRepository.findByBoarding(start,destination,date);
		System.out.println(findByBoarding);
		return findByBoarding;
	}

	@Override
	public void bookFlight(Flight flight, Passenger user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BookingDetails getBookingDetails(String pnr, String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
