package com.fbs.customer.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbs.customer.model.BookingDetails;
import com.fbs.customer.model.Flight;
import com.fbs.customer.model.Location;
import com.fbs.customer.model.Passenger;
import com.fbs.customer.model.Schedule;
import com.fbs.customer.repository.BookingDetailsRepository;
import com.fbs.customer.repository.CustomScheduleRepository;
import com.fbs.customer.repository.ScheduleRepository;


@Service
public class FlightBookingService implements IFlightBookingService{
	
	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Autowired
	CustomScheduleRepository customScheduleRepository;
	
	@Autowired
	BookingDetailsRepository bookingDetailsRepository; 
	
	@Override
	public List<Schedule> getFlightDetails(String start, String destination, Date date) {
		return customScheduleRepository.findFlightSchedules(start,destination,date);
	}

	@Override
	public void bookFlight(Schedule schedule, Passenger passenger) {
		// TODO Auto-generated method stub
		BookingDetails bookingDetails = new BookingDetails();
		// TODO Check Seat Availability
		// TODO Make Payment
		
		Flight flight = schedule.getFlight();
		Location startLocation = schedule.getBoarding().getLocation();
		Location endLocation = schedule.getDestination().getLocation();
		Date startTime = schedule.getStartTime();
		
		bookingDetails.setFlight(flight);
		bookingDetails.setPassenger(passenger);
		bookingDetails.setStartLocation(startLocation);
		bookingDetails.setEndLocation(endLocation);
		bookingDetails.setBoardingDate(startTime);
		String PNR = generatePNR(flight, schedule);
		bookingDetails.setPNR(PNR);
		
		bookingDetailsRepository.save(bookingDetails);
		// TODO SEND EMAIL
		
	}

	private String generatePNR(Flight flight, Schedule schedule) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(flight.getAirlineCompany().getCode());
		sb.append(schedule.getBoarding().getCode());
		sb.append(schedule.getDestination().getCode());
		
		// TODO get seats booked count and append it sb
		sb.append(001);
		
		
		return sb.toString();
	}

	@Override
	public BookingDetails getBookingDetails(String pnr, String email) {
		// TODO Auto-generated method stub
		return bookingDetailsRepository.findByPNRAndPassenger_Email(pnr,email);
	}

}
