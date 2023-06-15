package com.fbs.auth.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fbs.auth.exception.FlightException;
import com.fbs.auth.model.Flight;

@FeignClient(name = "flight", url = "http://localhost:9091/api/flight")
public interface FlightProxy {
	@PostMapping("/addFlight")
	public ResponseEntity<Flight> addFlight(@RequestBody Flight Flight) throws FlightException;

	@GetMapping("/getAllFlights")
	public ResponseEntity<List<Flight>> getAllFlights() throws FlightException;

	@DeleteMapping("/deleteFlight")
	public ResponseEntity<String> deleteFlight(@RequestBody Flight Flight) throws FlightException;

	@PutMapping("/updateFlight")
	public ResponseEntity<Flight>  updateFlight(@RequestBody Flight Flight) throws FlightException;
}
