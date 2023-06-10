package com.fbs.fare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fbs.fare.model.Flight;
import com.fbs.fare.model.Schedule;
import com.fbs.fare.service.FareService;

@RestController
public class FareController {
	
	@Autowired
	private FareService fareService;
	
	@PostMapping("/setFlightSeat")
	public Flight setFlightSeat(@RequestBody Flight flight) {
		return fareService.setFlightSeat(flight);
	}
	
	@PostMapping("/setFareForEachSeat")
	public Schedule setFareForEachSeat(@RequestBody Schedule schedule) {
		return fareService.setFareForEachSeat(schedule);
	}
}
