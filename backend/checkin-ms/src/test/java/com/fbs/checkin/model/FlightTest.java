package com.fbs.checkin.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class FlightTest {

	@Test
	void testConstructorAndGetters() {
		Airline airline = new Airline("1", "ABC", "Test Airline", null);
		Flight flight = new Flight("1", "fl01", airline, 90, 6, null, null);

		assertEquals("1", flight.getId());
		assertEquals("fl01", flight.getFlightNumber());
		assertEquals(airline, flight.getAirlineCompany());
		assertEquals(90, flight.getSeatCapacity());
		assertEquals(6, flight.getNumberOfColumns());
		assertNull(flight.getSeat());
		assertNull(flight.getSchedules());
	}

	@Test
	void testSetters() {
		Flight flight = new Flight();
		Airline airline = new Airline("1", "ABC", "Test Airline", null);

		flight.setId("1");
		flight.setSeatCapacity(90);
		flight.setNumberOfColumns(6);
		flight.setFlightNumber("fl01");
		flight.setAirlineCompany(airline);
		flight.setSeat(null);
		flight.setSchedules(null);

		assertEquals("1", flight.getId());
		assertEquals("fl01", flight.getFlightNumber());
		assertEquals(airline, flight.getAirlineCompany());
		assertEquals(90, flight.getSeatCapacity());
		assertEquals(6, flight.getNumberOfColumns());
		assertNull(flight.getSeat());
		assertNull(flight.getSchedules());
	}

	@Test
	void testToString() {
		Airline airline = new Airline("1", "ABC", "Test Airline", null);
		Flight flight = new Flight("1", "fl01", airline, 90, 6, null, null);

		String expectedString = "Flight(id=1, flightNumber=fl01, airlineCompany=Airline(id=1, code=ABC, airlineName=Test Airline, flights=null), seatCapacity=90, numberOfColumns=6, seat=null, schedules=null)";
		assertEquals(expectedString, flight.toString());
	}

	@Test
	void testAllArgsConstructor() {
		Airline airline = new Airline("1", "ABC", "Test Airline", null);
		Flight flight = new Flight("1", "fl01", airline, 90, 6, null, null);

		assertEquals("1", flight.getId());
		assertEquals("fl01", flight.getFlightNumber());
		assertEquals(airline, flight.getAirlineCompany());
		assertEquals(90, flight.getSeatCapacity());
		assertEquals(6, flight.getNumberOfColumns());
		assertNull(flight.getSeat());
		assertNull(flight.getSchedules());
	}

	@Test
	void testNoArgsConstructor() {
		Flight flight = new Flight();

		assertNotNull(flight);
	}
}
