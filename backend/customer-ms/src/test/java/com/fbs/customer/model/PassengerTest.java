package com.fbs.customer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class PassengerTest {

	@Test
	void testConstructorAndGetters() {
		Passenger passenger = new Passenger("1", "abc", "email.example.com",1234567890,null);

		assertEquals("1", passenger.getId());
		assertEquals("abc", passenger.getName());
		assertEquals("email.example.com", passenger.getEmail());
		assertEquals(1234567890, passenger.getPhoneNumber());
		assertNull(passenger.getBookingDetails());
	}

	@Test
	void testSetters() {
		Passenger passenger = new Passenger();

		passenger.setId("1");
		passenger.setName("abc");
		passenger.setEmail("email.example.com");
		passenger.setPhoneNumber(1234567890);
		passenger.setBookingDetails(null);

		assertEquals("1", passenger.getId());
		assertEquals("abc", passenger.getName());
		assertEquals("email.example.com", passenger.getEmail());
		assertEquals(1234567890, passenger.getPhoneNumber());
		assertNull(passenger.getBookingDetails());
	}

	@Test
	void testToString() {
		Passenger passenger = new Passenger("1", "abc", "email.example.com",1234567890,null);

		String expectedString = "Passenger(id=1, name=abc, email=email.example.com, phoneNumber=1234567890, bookingDetails=null)";
		assertEquals(expectedString, passenger.toString());
	}

	@Test
	void testAllArgsConstructor() {
		Passenger passenger = new Passenger("1", "abc", "email.example.com",1234567890,null);

		assertEquals("1", passenger.getId());
		assertEquals("abc", passenger.getName());
		assertEquals("email.example.com", passenger.getEmail());
		assertEquals(1234567890, passenger.getPhoneNumber());
		assertNull(passenger.getBookingDetails());
	}

	@Test
	void testNoArgsConstructor() {
		Passenger passenger = new Passenger();

		assertNotNull(passenger);
	}
}
