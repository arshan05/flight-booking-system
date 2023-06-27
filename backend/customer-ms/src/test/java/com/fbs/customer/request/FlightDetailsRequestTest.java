package com.fbs.customer.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

class FlightDetailsRequestTest {

	@Test
	void testConstructorAndGetters() {
		FlightDetailsRequest flightDetailsRequest = new FlightDetailsRequest("Bengaluru","Delhi",new Date("07/06/2023"));

		assertEquals("Bengaluru", flightDetailsRequest.getStart());
		assertEquals("Delhi", flightDetailsRequest.getDestination());
		assertEquals(new Date("07/06/2023"), flightDetailsRequest.getDate());
	}

	@Test
	void testSetters() {
		FlightDetailsRequest flightDetailsRequest = new FlightDetailsRequest();

		flightDetailsRequest.setStart("Bengaluru");
		flightDetailsRequest.setDestination("Delhi");
		flightDetailsRequest.setDate(new Date("07/06/2023"));
		
		assertEquals("Bengaluru", flightDetailsRequest.getStart());
		assertEquals("Delhi", flightDetailsRequest.getDestination());
		assertEquals(new Date("07/06/2023"), flightDetailsRequest.getDate());
	}

	@Test
	void testToString() {
		FlightDetailsRequest flightDetailsRequest = new FlightDetailsRequest("Bengaluru","Delhi",new Date("07/06/2023"));
		
		String expectedString = "FlightDetailsRequest(start=Bengaluru, destination=Delhi, date=Thu Jul 06 00:00:00 IST 2023)";
		assertEquals(expectedString, flightDetailsRequest.toString());
	}

	@Test
	void testAllArgsConstructor() {
		FlightDetailsRequest flightDetailsRequest = new FlightDetailsRequest("Bengaluru","Delhi",new Date("07/06/2023"));
		
		assertEquals("Bengaluru", flightDetailsRequest.getStart());
		assertEquals("Delhi", flightDetailsRequest.getDestination());
		assertEquals(new Date("07/06/2023"), flightDetailsRequest.getDate());
	}

	@Test
	void testNoArgsConstructor() {
		FlightDetailsRequest flightDetailsRequest = new FlightDetailsRequest();

		assertNotNull(flightDetailsRequest);
	}
}

