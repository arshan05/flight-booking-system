package com.fbs.gateway.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fbs.gateway.exception.AirportException;
import com.fbs.gateway.model.Airport;

@FeignClient(name = "airport", url = "http://localhost:9091/api/airport")
public interface AirportProxy {
	@PostMapping("/addAirport")
	public ResponseEntity<Airport> addAirport(@RequestBody Airport Airport) throws AirportException;

	@GetMapping("/getAllAirports")
	public ResponseEntity<List<Airport>> getAllAirports() throws AirportException;

	@DeleteMapping("/deleteAirport")
	public ResponseEntity<String> deleteAirine(@RequestBody Airport Airport) throws AirportException;

	@PutMapping("/updateAirport")
	public ResponseEntity<Airport>  updateAirport(@RequestBody Airport Airport) throws AirportException;
}
