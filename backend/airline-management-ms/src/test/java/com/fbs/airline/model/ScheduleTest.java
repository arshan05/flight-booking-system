package com.fbs.airline.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

class ScheduleTest {

//	private String id;
//	private Flight flight;
//	private Airport boarding;
//	private Airport destination;
//	private Date startTime;
//	private Date endTime;
//	private Status scheduleStatus;
//	private List<FlightSeat> fare;
//	private int baseFare;

	@Test
	void testConstructorAndGetters() {
		Airline airline = new Airline("1", "ABC", "Test Airline", null);
		Flight flight = new Flight("1", "fl01", airline, 90, 6, null, null);
		
		Schedule schedule = new Schedule("1", flight, null, null,new Date("23/12/2023"),new Date("23/12/2023"),Status.ONTIME,null,10000);

		assertEquals("1", schedule.getId());
		assertEquals(flight, schedule.getFlight());
		assertNull(schedule.getBoarding());
		assertNull(schedule.getDestination());
		assertEquals(new Date("23/12/2023"), schedule.getStartTime());
		assertEquals(new Date("23/12/2023"), schedule.getEndTime());
		assertEquals(Status.ONTIME, schedule.getScheduleStatus());
		assertNull(schedule.getFare());
		assertEquals(10000,schedule.getBaseFare());
	}

	@Test
	void testSetters() {
		Airline airline = new Airline("1", "ABC", "Test Airline", null);
		Flight flight = new Flight("1", "fl01", airline, 90, 6, null, null);
		
		Schedule schedule = new Schedule();

		schedule.setId("1");
		schedule.setFlight(flight);
		schedule.setBoarding(null);
		schedule.setDestination(null);
		schedule.setStartTime(new Date("23/12/2023"));
		schedule.setEndTime(new Date("23/12/2023"));
		schedule.setScheduleStatus(Status.ONTIME);
		schedule.setFare(null);
		schedule.setBaseFare(10000);

		assertEquals("1", schedule.getId());
		assertEquals(flight, schedule.getFlight());
		assertNull(schedule.getBoarding());
		assertNull(schedule.getDestination());
		assertEquals(new Date("23/12/2023"), schedule.getStartTime());
		assertEquals(new Date("23/12/2023"), schedule.getEndTime());
		assertEquals(Status.ONTIME, schedule.getScheduleStatus());
		assertNull(schedule.getFare());
		assertEquals(10000,schedule.getBaseFare());
	}

	@Test
	void testToString() {
		Airline airline = new Airline("1", "ABC", "Test Airline", null);
		Flight flight = new Flight("1", "fl01", airline, 90, 6, null, null);
		
		Schedule schedule = new Schedule("1", flight, null, null,new Date("23/12/2023"),new Date("23/12/2023"),Status.ONTIME,null,1000);

		String expectedString = "Schedule(id=1, flight=Flight(id=1, flightNumber=fl01, airlineCompany=Airline(id=1, code=ABC, airlineName=Test Airline, flights=null), seatCapacity=90, numberOfColumns=6, seat=null, schedules=null), boarding=null, destination=null, startTime=Tue Nov 12 00:00:00 IST 2024, endTime=Tue Nov 12 00:00:00 IST 2024, scheduleStatus=ONTIME, fare=null, baseFare=1000)";
		assertEquals(expectedString, schedule.toString());
	}

	@Test
	void testAllArgsConstructor() {

		Airline airline = new Airline("1", "ABC", "Test Airline", null);
		Flight flight = new Flight("1", "fl01", airline, 90, 6, null, null);
		
		Schedule schedule = new Schedule("1", flight, null, null,new Date("23/12/2023"),new Date("23/12/2023"),Status.ONTIME,null,10000);
		
		assertEquals("1", schedule.getId());
		assertEquals(flight, schedule.getFlight());
		assertNull(schedule.getBoarding());
		assertNull(schedule.getDestination());
		assertEquals(new Date("23/12/2023"), schedule.getStartTime());
		assertEquals(new Date("23/12/2023"), schedule.getEndTime());
		assertEquals(Status.ONTIME, schedule.getScheduleStatus());
		assertNull(schedule.getFare());
		assertEquals(10000,schedule.getBaseFare());
	}

	@Test
	void testNoArgsConstructor() {
		Schedule schedule = new Schedule();

		assertNotNull(schedule);
	}
}
