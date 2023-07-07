package com.fbs.customer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

class BookingDetailsTest {

	@Test
	void testConstructorAndGetters() {
		Airline airline = new Airline("1", "ABC", "Test Airline", null);
		Flight flight = new Flight("1", "fl01", airline, 90, 6, null, null);
		Passenger passenger = new Passenger("1", "abc", "email.example.com", 1234567890, null);
		Schedule schedule = new Schedule("1", flight, null, null, new Date("23/12/2023"), new Date("23/12/2023"),
				Status.ONTIME, null, 10000);

		BookingDetails bookingDetails = new BookingDetails("1", passenger, schedule, "A01", "ABC1234", false);

		assertEquals("1", bookingDetails.getId());
		assertEquals(passenger, bookingDetails.getPassenger());
		assertEquals(schedule, bookingDetails.getSchedule());
		assertEquals("ABC1234", bookingDetails.getPNR());
		assertEquals("A01", bookingDetails.getSeatNumber());
		assertEquals(false, bookingDetails.isCheckedIn());
	}

	@Test
	void testSetters() {
		Airline airline = new Airline("1", "ABC", "Test Airline", null);
		Flight flight = new Flight("1", "fl01", airline, 90, 6, null, null);
		Passenger passenger = new Passenger("1", "abc", "email.example.com", 1234567890, null);
		Schedule schedule = new Schedule("1", flight, null, null, new Date("23/12/2023"), new Date("23/12/2023"),
				Status.ONTIME, null, 10000);
		BookingDetails bookingDetails = new BookingDetails();

		bookingDetails.setId("1");
		bookingDetails.setSchedule(schedule);
		bookingDetails.setPassenger(passenger);
		bookingDetails.setPNR("ABC1234");
		bookingDetails.setSeatNumber("A01");
		bookingDetails.setCheckedIn(false);

		assertEquals("1", bookingDetails.getId());
		assertEquals(schedule, bookingDetails.getSchedule());
		assertEquals(passenger, bookingDetails.getPassenger());
		assertEquals("ABC1234", bookingDetails.getPNR());
		assertEquals("A01", bookingDetails.getSeatNumber());
		assertEquals(false, bookingDetails.isCheckedIn());
	}

	@Test
	void testToString() {
		Airline airline = new Airline("1", "ABC", "Test Airline", null);
		Flight flight = new Flight("1", "fl01", airline, 90, 6, null, null);
		Passenger passenger = new Passenger("1", "abc", "email.example.com", 1234567890, null);
		Schedule schedule = new Schedule("1", flight, null, null, new Date("23/12/2023"), new Date("23/12/2023"),
				Status.ONTIME, null, 10000);

		BookingDetails bookingDetails = new BookingDetails("1", passenger, schedule, "A01", "ABC1234", false);

		String expectedString = "BookingDetails(id=1, passenger=Passenger(id=1, name=abc, email=email.example.com, phoneNumber=1234567890, bookingDetails=null), schedule=Schedule(id=1, flight=Flight(id=1, flightNumber=fl01, airlineCompany=Airline(id=1, code=ABC, airlineName=Test Airline, flights=null), seatCapacity=90, numberOfColumns=6, seat=null, schedules=null), boarding=null, destination=null, startTime=Tue Nov 12 00:00:00 IST 2024, endTime=Tue Nov 12 00:00:00 IST 2024, scheduleStatus=ONTIME, fare=null, baseFare=10000), seatNumber=A01, PNR=ABC1234, isCheckedIn=false)";
		assertEquals(expectedString, bookingDetails.toString());
	}

	@Test
	void testAllArgsConstructor() {

		Airline airline = new Airline("1", "ABC", "Test Airline", null);
		Flight flight = new Flight("1", "fl01", airline, 90, 6, null, null);
		Passenger passenger = new Passenger("1", "abc", "email.example.com", 1234567890, null);
		Schedule schedule = new Schedule("1", flight, null, null, new Date("23/12/2023"), new Date("23/12/2023"),
				Status.ONTIME, null, 10000);

		BookingDetails bookingDetails = new BookingDetails("1", passenger, schedule, "A01", "ABC1234", false);

		assertEquals("1", bookingDetails.getId());
		assertEquals(schedule, bookingDetails.getSchedule());
		assertEquals(passenger, bookingDetails.getPassenger());
		assertEquals("ABC1234", bookingDetails.getPNR());
		assertEquals("A01", bookingDetails.getSeatNumber());
		assertEquals(false, bookingDetails.isCheckedIn());
	}

	@Test
	void testNoArgsConstructor() {
		BookingDetails bookingDetails = new BookingDetails();

		assertNotNull(bookingDetails);
	}
}
