package com.fbs.airline.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AirlineTest {

	@Test
	void test() {
		Flight flight1 = new Flight();
		flight1.setId("1");
		flight1.setFlightNumber("FL001");

		// Create a list of Flights
		List<Flight> flights = new ArrayList<>();
		flights.add(flight1);

		// Create an Airline
		Airline airline = new Airline("1", "AA", "American Airlines", flights);

		// Assert the properties of the Airline
		Assertions.assertEquals("1", airline.getId());
		Assertions.assertEquals("AA", airline.getCode());
		Assertions.assertEquals("American Airlines", airline.getAirlineName());
		Assertions.assertEquals(flights, airline.getFlights());
	}

}
