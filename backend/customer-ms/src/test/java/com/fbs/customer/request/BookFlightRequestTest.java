package com.fbs.customer.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.fbs.customer.model.Airline;
import com.fbs.customer.model.Flight;
import com.fbs.customer.model.Passenger;
import com.fbs.customer.model.Schedule;
import com.fbs.customer.model.Status;

class BookFlightRequestTest {

	@Test
	void testConstructorAndGetters() {
		Passenger passenger = new Passenger("1", "abc", "email.example.com",1234567890,null);
		Airline airline = new Airline("1", "ABC", "Test Airline", null);
		Flight flight = new Flight("1", "fl01", airline, 90, 6, null, null);
		
		Schedule schedule = new Schedule("1", flight, null, null,new Date("23/12/2023"),new Date("23/12/2023"),Status.ONTIME,null,10000);
		BookFlightRequest bookFlightRequest = new BookFlightRequest(schedule,passenger,"A01");

		assertEquals(passenger,bookFlightRequest.getPassenger());
		assertEquals(schedule,bookFlightRequest.getSchedule());
		assertEquals("A01", bookFlightRequest.getSeatNumber());
	}

	@Test
	void testSetters() {
		BookFlightRequest bookFlightRequest = new BookFlightRequest();
		
		Passenger passenger = new Passenger("1", "abc", "email.example.com",1234567890,null);
		
		Airline airline = new Airline("1", "ABC", "Test Airline", null);
		Flight flight = new Flight("1", "fl01", airline, 90, 6, null, null);
		
		Schedule schedule = new Schedule("1", flight, null, null,new Date("23/12/2023"),new Date("23/12/2023"),Status.ONTIME,null,10000);

		bookFlightRequest.setPassenger(passenger);
		bookFlightRequest.setSchedule(schedule);
		bookFlightRequest.setSeatNumber("A01");

		assertEquals(passenger,bookFlightRequest.getPassenger());
		assertEquals(schedule,bookFlightRequest.getSchedule());
		assertEquals("A01", bookFlightRequest.getSeatNumber());
	}

	@Test
	void testToString() {
		Passenger passenger = new Passenger("1", "abc", "email.example.com",1234567890,null);
		Airline airline = new Airline("1", "ABC", "Test Airline", null);
		Flight flight = new Flight("1", "fl01", airline, 90, 6, null, null);
		
		Schedule schedule = new Schedule("1", flight, null, null,new Date("23/12/2023"),new Date("23/12/2023"),Status.ONTIME,null,10000);
		BookFlightRequest bookFlightRequest = new BookFlightRequest(schedule,passenger,"A01");
		
		String expectedString = "BookFlightRequest(schedule=Schedule(id=1, flight=Flight(id=1, flightNumber=fl01, airlineCompany=Airline(id=1, code=ABC, airlineName=Test Airline, flights=null), seatCapacity=90, numberOfColumns=6, seat=null, schedules=null), boarding=null, destination=null, startTime=Tue Nov 12 00:00:00 IST 2024, endTime=Tue Nov 12 00:00:00 IST 2024, scheduleStatus=ONTIME, fare=null, baseFare=10000), passenger=Passenger(id=1, name=abc, email=email.example.com, phoneNumber=1234567890, bookingDetails=null), seatNumber=A01)";
		assertEquals(expectedString, bookFlightRequest.toString());
	}

	@Test
	void testAllArgsConstructor() {
		Passenger passenger = new Passenger("1", "abc", "email.example.com",1234567890,null);
		Airline airline = new Airline("1", "ABC", "Test Airline", null);
		Flight flight = new Flight("1", "fl01", airline, 90, 6, null, null);
		
		Schedule schedule = new Schedule("1", flight, null, null,new Date("23/12/2023"),new Date("23/12/2023"),Status.ONTIME,null,10000);
		BookFlightRequest bookFlightRequest = new BookFlightRequest(schedule,passenger,"A01");
		
		assertEquals(passenger,bookFlightRequest.getPassenger());
		assertEquals(schedule,bookFlightRequest.getSchedule());
		assertEquals("A01", bookFlightRequest.getSeatNumber());
	}

	@Test
	void testNoArgsConstructor() {
		BookFlightRequest bookFlightRequest = new BookFlightRequest();

		assertNotNull(bookFlightRequest);
	}
}

