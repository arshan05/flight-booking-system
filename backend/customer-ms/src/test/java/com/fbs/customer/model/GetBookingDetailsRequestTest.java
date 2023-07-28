package com.fbs.customer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class GetBookingDetailsRequestTest {

	@Test
	void testConstructorAndGetters() {
		GetBookingDetailsRequest getBookingDetailsRequest = new GetBookingDetailsRequest("pnrNumber","abc@gmail.com");

		assertEquals("pnrNumber", getBookingDetailsRequest.getPnr());
		assertEquals("abc@gmail.com", getBookingDetailsRequest.getEmail());
		
	}

	@Test
	void testSetters() {
		GetBookingDetailsRequest getBookingDetailsRequest = new GetBookingDetailsRequest();

		getBookingDetailsRequest.setPnr("pnrNumber");
		getBookingDetailsRequest.setEmail("abc@gmail.com");

		assertEquals("pnrNumber", getBookingDetailsRequest.getPnr());
		assertEquals("abc@gmail.com", getBookingDetailsRequest.getEmail());
	}

	@Test
	void testToString() {
		GetBookingDetailsRequest getBookingDetailsRequest = new GetBookingDetailsRequest("pnrNumber","abc@gmail.com");

		String expectedString = "GetBookingDetailsRequest(pnr=pnrNumber, email=abc@gmail.com)";
		assertEquals(expectedString, getBookingDetailsRequest.toString());
	}

	@Test
	void testAllArgsConstructor() {
		GetBookingDetailsRequest getBookingDetailsRequest = new GetBookingDetailsRequest("pnrNumber","abc@gmail.com");

		assertEquals("pnrNumber", getBookingDetailsRequest.getPnr());
		assertEquals("abc@gmail.com", getBookingDetailsRequest.getEmail());
	}

	@Test
	void testNoArgsConstructor() {
		GetBookingDetailsRequest getBookingDetailsRequest = new GetBookingDetailsRequest();

		assertNotNull(getBookingDetailsRequest);
	}

}
