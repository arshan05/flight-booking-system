package com.fbs.customer.service;

import java.util.Date;
import java.util.List;
import java.util.Random;
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
import com.fbs.customer.repository.CustomBookingDetailsRepository;
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
	CustomBookingDetailsRepository customBookingDetailsRepository;

	@Autowired
	BookingDetailsRepository bookingDetailsRepository;

	@Autowired
	PassengerRepository passengerRepository;

	@Autowired
	EmailProxy emailProxy;

	@Override
	public List<Schedule> getFlightDetails(String start, String destination, Date date) {
		System.out.println(start);
		return customScheduleRepository.findFlightSchedules(start, destination, date);
	}

	@Override
	public BookingDetails bookFlight(Schedule schedule, Passenger passenger, String seatNumber) {
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

		bookingDetails.setSchedule(schedule);
		bookingDetails.setPassenger(passenger);
		String PNR = generatePNR(flight, schedule);
		bookingDetails.setPNR(PNR);
		bookingDetails.setSeatNumber(seatNumber);
		bookingDetails.setCheckedIn(false);

		System.out.println(bookingDetails);
		scheduleRepository.save(schedule);

		bookingDetailsRepository.save(bookingDetails);
		String messageBody = "Dear " + passenger.getName() + "\n You have successfully booked a seat from "
				+ startLocation.getPlace() + " to " + endLocation.getPlace() + ". Happy Journey🎉🎉 \n PNR number: " + PNR;

		EmailRequest emailRequest = new EmailRequest(passenger.getEmail(), "Booking Successful", messageBody);
		System.out.println(emailRequest);
		emailProxy.sendEmail(emailRequest);
		return bookingDetails;
	}

	public String generatePNR(Flight flight, Schedule schedule) {
		String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

		int pnrLength = 8;

		Random random = new Random();

		StringBuilder pnrBuilder = new StringBuilder();

		for (int i = 0; i < pnrLength; i++) {
			int randomIndex = random.nextInt(allowedChars.length());
			char randomChar = allowedChars.charAt(randomIndex);
			pnrBuilder.append(randomChar);
		}

		return pnrBuilder.toString();

	}

	@Override
	public List<BookingDetails> getBookingDetailsByPnrAndEmail(String pnr, String email) {
		return customBookingDetailsRepository.findAllBookingsByPnrAndEmail(pnr, email);
	}

	@Override
	public List<BookingDetails> getBookingDetailsByEmail(String email) {
		return customBookingDetailsRepository.findAllBookingsByEmail(email);
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
		Schedule schedule = bookingDetails.getSchedule();
		List<FlightSeat> fare = schedule.getFare();

		for (FlightSeat flightSeat : fare) {
			if (flightSeat.getSeatNumber().equals(flightSeat.getSeatNumber())) {
				flightSeat.setSeatStatus(SeatStatus.OPEN);
			}
		}
		schedule.setFare(fare);
		scheduleRepository.save(schedule);
		bookingDetailsRepository.delete(bookingDetails);
		
		Passenger passenger = bookingDetails.getPassenger();
		Flight flight = schedule.getFlight();
		
		
		String messageBody = "Dear "+passenger.getName() + ",\r\n"
				+ "\r\n"
				+ "We have successfully canceled your flight ticket as per your request. Here are the details of your canceled booking:\r\n"
				+ "\r\n"
				+ "PNR number: " +bookingDetails.getPNR() + "\r\n"
				+ "Flight Number: "+ flight.getFlightNumber()+", "+flight.getAirlineCompany() +"\r\n"
				+ "Departure: "+ schedule.getBoarding().getLocation().getPlace() +"\r\n"
				+ "Destination: "+ schedule.getDestination().getLocation().getPlace() +"\r\n"
				+ "Cancellation Date: "+ new Date() +"\r\n"
				+ "\r\n"
				+ "Thank you for choosing our services.\r\n"
				+ "\r\n"
				+ "Best regards,\r\n"
				+ "Escapes";
		EmailRequest emailRequest = new EmailRequest(passenger.getEmail(), "Booking Successful", messageBody);
		System.out.println(emailRequest);
		emailProxy.sendEmail(emailRequest);
	}

	public Passenger addPassenger(Passenger passenger) {
		// TODO Auto-generated method stub
		return passengerRepository.save(passenger);

	}

}
