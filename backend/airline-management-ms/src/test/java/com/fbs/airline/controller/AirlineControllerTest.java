//package com.fbs.airline.controller;
//
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.data.mongodb.core.mapping.DBRef;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fbs.airline.exception.AirlineException;
//import com.fbs.airline.model.Airline;
//import com.fbs.airline.model.Flight;
//import com.fbs.airline.service.AirlineService;
//import com.fbs.airline.service.AuthService;
//
//class AirlineControllerTest {
//	
//	@Mock
//    private AirlineService airlineService;
//
//    @Mock
//    private AuthService authService;
//
//    @InjectMocks
//    private AirlineController airlineController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//	@Test
//	void testAddAirline() throws AirlineException {
//		Airline airline = new Airline();
//		airline.setCode("AIR");
//		airline.setAirlineName("air one");
//		
////		private String id;
////		private String code;
////		private String airlineName;
////		
////		@DBRef(lazy = true)
////		@JsonIgnoreProperties("airlineCompany")
////		private List<Flight> flights;
//		
//		List<Airline> airlines = new ArrayList<>();
//		
//		when(airlines.add(airline)).thenReturn(airline);
//		
//		
//		
//		
//	}
//
//	@Test
//	void testGetAllAirlines() {
//		
//	}
//
//	@Test
//	void testDeleteAirine() {
//		
//	}
//
//	@Test
//	void testUpdateAirline() {
//		
//	}
//
//}
