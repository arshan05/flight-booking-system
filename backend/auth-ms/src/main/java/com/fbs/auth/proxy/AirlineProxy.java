package com.fbs.auth.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fbs.auth.exception.AirlineException;
import com.fbs.auth.model.Airline;

@FeignClient(name = "airline", url = "http://localhost:9091/api/airline")
public interface AirlineProxy {
	@PostMapping("/addAirline")
	public ResponseEntity<Airline> addAirline(@RequestBody Airline airline) throws AirlineException;

	@GetMapping("/getAllAirlines")
	public ResponseEntity<List<Airline>> getAllAirlines() throws AirlineException;

	@DeleteMapping("/deleteAirline")
	public ResponseEntity<String> deleteAirine(@RequestBody Airline airline) throws AirlineException;

	@PutMapping("/updateAirline")
	public ResponseEntity<Airline>  updateAirline(@RequestBody Airline airline) throws AirlineException;
}
