package com.fbs.customer.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbs.customer.model.BookingDetails;
import com.fbs.customer.model.EmailRequest;
import com.fbs.customer.model.Flight;
import com.fbs.customer.model.FlightSeat;
import com.fbs.customer.model.Location;
import com.fbs.customer.model.Passenger;
import com.fbs.customer.model.Schedule;
import com.fbs.customer.model.SeatStatus;
import com.fbs.customer.proxy.EmailProxy;
import com.fbs.customer.repository.BookingDetailsRepository;
import com.fbs.customer.repository.CustomScheduleRepository;
import com.fbs.customer.repository.PassengerRepository;
import com.fbs.customer.repository.ScheduleRepository;

@Service
public class FlightBookingService implements IFlightBookingService {

	@Autowired
	ScheduleRepository scheduleRepository;

	@Autowired
	CustomScheduleRepository customScheduleRepository;

	@Autowired
	BookingDetailsRepository bookingDetailsRepository;

	@Autowired
	PassengerRepository passengerRepository;

	@Autowired
	EmailProxy emailProxy;

	@Override
	public List<Schedule> getFlightDetails(String start, String destination, Date date) {
		return customScheduleRepository.findFlightSchedules(start, destination, date);
	}

	@Override
	public void bookFlight(Schedule schedule, Passenger passenger, String seatNumber) {
		// TODO Auto-generated method stub
		BookingDetails bookingDetails = new BookingDetails();

		// TODO Make Payment
		schedule = scheduleRepository.findById(schedule.getId()).orElse(null);
		passenger = passengerRepository.findById(passenger.getId()).orElse(null);
		double fareAmount = 0;
		for (FlightSeat seat : schedule.getFare()) {
			if (seat.getSeatNumber().equals(seatNumber)) {
				seat.setSeatStatus(SeatStatus.BOOKED);
				fareAmount = seat.getPrice();
			}
		}
		System.out.println("=========schedule==========");
		System.out.println(schedule.getFare());

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
		bookingDetails.setSeatNumber(seatNumber);

		System.out.println(bookingDetails);

//		bookingDetailsRepository.save(bookingDetails);
		String messageBody = "Dear " + passenger.getName() + " /n You have booked seat from " + startLocation.getPlace()
				+ " to " + endLocation.getPlace() + " is successful/n PNR number: " + PNR;
		EmailRequest emailRequest = new EmailRequest(passenger.getEmail(), "Booking Successful", messageBody);
//		emailProxy.sendEmail(emailRequest);
	}

	public String generatePNR(Flight flight, Schedule schedule) {
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
		return bookingDetailsRepository.findByPNRAndPassenger_Email(pnr, email);
	}

	public List<FlightSeat> getAvailableSeats(Schedule schedule) {
		// TODO Auto-generated method stub
		schedule = scheduleRepository.findById(schedule.getId()).orElse(null);
		System.out.println(schedule);
		List<FlightSeat> flightSeats = schedule.getFare().stream()
				.filter(seat -> seat.getSeatStatus().equals(SeatStatus.OPEN)).collect(Collectors.toList());

		return flightSeats;
	}

	@Override
	public void cancelTicket(BookingDetails bookingDetails) {
		// TODO Auto-generated method stub
		bookingDetails.getSeatNumber();
		
	}

}
