package com.fbs.checkin.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class FlightSeatTest {

	@Test
	void testConstructorAndGetters() {
		FlightSeat flightSeat = new FlightSeat("1", "Economy",1000,SeatStatus.OPEN);

		assertEquals("1", flightSeat.getSeatNumber());
		assertEquals("Economy", flightSeat.getClassName());
		assertEquals(1000, flightSeat.getPrice());
		assertEquals(SeatStatus.OPEN, flightSeat.getSeatStatus());
	}

	@Test
	void testSetters() {
		FlightSeat flightSeat = new FlightSeat();

		flightSeat.setSeatNumber("1");
		flightSeat.setClassName("Economy");
		flightSeat.setPrice(1000);
		flightSeat.setSeatStatus(SeatStatus.OPEN);

		assertEquals("1", flightSeat.getSeatNumber());
		assertEquals("Economy", flightSeat.getClassName());
		assertEquals(1000, flightSeat.getPrice());
		assertEquals(SeatStatus.OPEN, flightSeat.getSeatStatus());
	}

	@Test
	void testToString() {
		FlightSeat flightSeat = new FlightSeat("1", "Economy",1000,SeatStatus.OPEN);
		
		String expectedString = "FlightSeat(price=1000, seatStatus=OPEN)";
		assertEquals(expectedString, flightSeat.toString());
	}

	@Test
	void testAllArgsConstructor() {
		FlightSeat flightSeat = new FlightSeat(1000,SeatStatus.OPEN);
		
		assertEquals(1000, flightSeat.getPrice());
		assertEquals(SeatStatus.OPEN, flightSeat.getSeatStatus());
	}

	@Test
	void testNoArgsConstructor() {
		FlightSeat FlightSeat = new FlightSeat();

		assertNotNull(FlightSeat);
	}
}

