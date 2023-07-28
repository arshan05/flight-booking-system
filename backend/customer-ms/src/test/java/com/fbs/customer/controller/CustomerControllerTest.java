package com.fbs.customer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.fbs.customer.model.BookingDetails;
import com.fbs.customer.model.FlightSeat;
import com.fbs.customer.model.GetBookingDetailsRequest;
import com.fbs.customer.model.Passenger;
import com.fbs.customer.model.Schedule;
import com.fbs.customer.request.BookFlightRequest;
import com.fbs.customer.service.AuthService;
import com.fbs.customer.service.IFlightBookingService;

class CustomerControllerTest {
	@Mock
	private IFlightBookingService flightBookingService;

	@Mock
	private AuthService authService;

	@InjectMocks
	private CustomerController customerController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetFlightDetails() {
		String start = "Bangalore";
		String destination = "Chennai";
		Date date = new Date();

		when(authService.isSessionValid("cookie")).thenReturn(true);
		when(flightBookingService.getFlightDetails(start, destination, date)).thenReturn(new ArrayList<>());

		List<Schedule> schedules = customerController.getFlightDetails(start, destination, date);

		assertEquals(0, schedules.size());
	}

	
	@Test
    void testGetAvailableSeats() {
        Schedule schedule = new Schedule();
        schedule.setId("1");

        when(authService.isSessionValid("cookie")).thenReturn(true);
        when(flightBookingService.getAvailableSeats(schedule)).thenReturn(new ArrayList<>());

        List<FlightSeat> flightSeats = customerController.getAvailableSeats("cookie",schedule);

        assertEquals(0, flightSeats.size());
    }
	
	@Test
	void testGetAvailableSeats_invalidSession() {
	    Schedule schedule = new Schedule();
	    schedule.setId("1");

	    when(authService.isSessionValid("cookie")).thenReturn(false);

	    assertThrows(ResponseStatusException.class, () -> customerController.getAvailableSeats("cookie",schedule));
	}
	
	@Test
	void testBookFlight() {
	    Schedule schedule = new Schedule();
	    schedule.setId("1");
	    Passenger passenger = new Passenger();
	    passenger.setName("John");
	    String seatNumber = "1A";
	    BookFlightRequest flightRequest = new BookFlightRequest(schedule, passenger, seatNumber);
	    when(authService.isSessionValid("cookie")).thenReturn(true);
	    BookingDetails bookingDetails = new BookingDetails();
		when(flightBookingService.bookFlight(schedule, passenger, seatNumber)).thenReturn(bookingDetails);

	    ResponseEntity<BookingDetails> response = customerController.bookFlight("cookie", flightRequest);
	    assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookingDetails, response.getBody());
	}
	
	@Test
	void testBookFlight_invalidSession() {
		BookFlightRequest bookFlightRequest = new BookFlightRequest();

	    when(authService.isSessionValid("cookie")).thenReturn(false);

	    assertThrows(ResponseStatusException.class, () -> customerController.bookFlight("cookie",bookFlightRequest));
	}
	
	@Test
    void testAddPassenger(){
        when(authService.isSessionValid(anyString())).thenReturn(true);

        Passenger passenger = new Passenger();
        when(flightBookingService.addPassenger(any(Passenger.class))).thenReturn(passenger);

        ResponseEntity<Passenger> response = customerController.addPassenger("cookie", passenger);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(passenger, response.getBody());

    }
	
	void testAddPassenger_invalidSession() {
		Passenger passenger = new Passenger();

	    when(authService.isSessionValid("cookie")).thenReturn(false);

	    assertThrows(ResponseStatusException.class, () -> customerController.addPassenger("cookie",passenger));
	}
	
	   @Test
	    void testGetBookingDetailsByPnrAndEmail() {
	        GetBookingDetailsRequest request = new GetBookingDetailsRequest();
	        String pnr = "ABC123";
	        String email = "test@example.com";
	        request.setPnr(pnr);
	        request.setEmail(email);

	        List<BookingDetails> bookingDetailsList = new ArrayList<>();

	        when(authService.isSessionValid("cookie")).thenReturn(true);
	        when(flightBookingService.getBookingDetailsByPnrAndEmail(pnr, email)).thenReturn(bookingDetailsList);

	        List<BookingDetails> response = customerController.getBookingDetails("cookie", request);
	        
	        assertEquals(0, response.size());

	    }
	   
	   void testGetBookingDetailsByPnrAndEmail_invalidSession() {
			GetBookingDetailsRequest request = new GetBookingDetailsRequest();

		    when(authService.isSessionValid("cookie")).thenReturn(false);

		    assertThrows(ResponseStatusException.class, () -> customerController.getBookingDetails("cookie",request));
		}
	   
	   @Test
	    void testGetBookingDetailsByEmail() {
	        String email = "test@example.com";
	        

	        List<BookingDetails> bookingDetailsList = new ArrayList<>();

	        when(authService.isSessionValid("cookie")).thenReturn(true);
	        when(flightBookingService.getBookingDetailsByEmail(email)).thenReturn(bookingDetailsList);

	        List<BookingDetails> response = customerController.getBookingDetails("cookie", email);
	        
	        assertEquals(0, response.size());

	    }
	   
	   void testGetBookingDetailsByEmail_invalidSession() {

		    when(authService.isSessionValid("cookie")).thenReturn(false);

		    assertThrows(ResponseStatusException.class, () -> customerController.getBookingDetails("cookie","email"));
		}
	   
	   @Test
	   void testCancelTicket() {
	       BookingDetails bookingDetails = new BookingDetails();
	       bookingDetails.setId("1");

	       when(authService.isSessionValid("cookie")).thenReturn(true);
	       doNothing().when(flightBookingService).cancelTicket(bookingDetails);

	       ResponseEntity<String> actualResponse = customerController.cancelTicket("cookie",bookingDetails);

	       assertEquals("Ticket canceled", actualResponse.getBody());
	   }

	   @Test
	   void testCancelTicket_invalidBookingDetails() {
	       BookingDetails bookingDetails = new BookingDetails();

	       when(authService.isSessionValid("cookie")).thenReturn(false);

	       assertThrows(ResponseStatusException.class, () -> customerController.cancelTicket("cookie",bookingDetails));
	   }
	
}
