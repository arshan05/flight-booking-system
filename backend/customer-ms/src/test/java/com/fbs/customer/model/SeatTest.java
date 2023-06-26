package com.fbs.customer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class SeatTest {

//	private String seatNumber;
//	private String className;

	@Test
	void testConstructorAndGetters() {
		Seat seat = new Seat("1", "Economy");

		assertEquals("1", seat.getSeatNumber());
		assertEquals("Economy", seat.getClassName());
	}

	@Test
	void testSetters() {
		Seat seat = new Seat();

		seat.setSeatNumber("1");
		seat.setClassName("Economy");

		assertEquals("1", seat.getSeatNumber());
		assertEquals("Economy", seat.getClassName());
	}

	@Test
	void testToString() {
		Seat seat = new Seat("1", "Economy");

		
		String expectedString = "Seat(seatNumber=1, className=Economy)";
		assertEquals(expectedString, seat.toString());
	}

	@Test
	void testAllArgsConstructor() {
		Seat seat = new Seat("1", "Economy");

		assertEquals("1", seat.getSeatNumber());
		assertEquals("Economy", seat.getClassName());
	}

	@Test
	void testNoArgsConstructor() {
		Seat Seat = new Seat();

		assertNotNull(Seat);
	}
}
