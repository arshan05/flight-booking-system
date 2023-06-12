package com.fbs.customer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.fbs.customer.model.Flight;
import com.fbs.customer.model.Schedule;
import com.fbs.customer.repository.BookingDetailsRepository;
import com.fbs.customer.repository.CustomScheduleRepository;
import com.fbs.customer.repository.ScheduleRepository;

public class FlightBookingServiceTest {
	
	@Mock
	ScheduleRepository scheduleRepository;
	
	@Mock
	CustomScheduleRepository customScheduleRepository;
	
	@Mock
	BookingDetailsRepository bookingDetailsRepository;
	
	@Mock 
	FlightBookingService bookingService;
	
	@Test
	private void generatePNRTest(Flight flight, Schedule schedule) {
		String required = "AIRBLRDEL001";
		String actual = bookingService.generatePNR(flight,schedule);
		assertEquals(required,actual);
	}
}
