//package com.fbs.airline.controller;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fbs.airline.model.Airline;
//import com.fbs.airline.model.Flight;
//import com.fbs.airline.service.AirlineService;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class AirlineControllerTest {
//
//	@Autowired
//	MockMvc mockMvc;
//
//	@Test
//	public void addAirlineTest() throws Exception {
//		List<Flight> flights = new ArrayList<>();
//		flights.add(new Flight());
//		Airline airline = new Airline("101", "AIR1", "air1", flights);
//
//		String cookie = "login=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkYXJ1bmRhcnNoYW4xMjIzQGdtYWlsLmNvbSIsImlhdCI6MTY4NzM0NzE5MiwiZXhwIjoxNjg3NDMzNTkyfQ.Lt4SxtIq9Spz0gaJdXjJ3Yu3O6Z2l0Xy-_vr1CQudjS-L3lBSWhBataRkkG8pb6XuCtG3gB2lBBSremvEshFWA";
//		AirlineService airlineService = Mockito.mock(AirlineService.class);
//
//		when(airlineService.addAirline(airline)).thenReturn(airline);
//		String url = "/api/airline/addAirline";
//
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//		ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
//		String reqstr = writer.writeValueAsString(airline);
//
//		mockMvc.perform(post(url).header("cookie", cookie).contentType("application/json").content(reqstr))
//				.andExpect(status().isOk());
//	}
//}
