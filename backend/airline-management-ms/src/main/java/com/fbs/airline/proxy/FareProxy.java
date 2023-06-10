package com.fbs.airline.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fbs.airline.model.Flight;
import com.fbs.airline.model.Schedule;


@FeignClient(name = "fare-ms", url="http://localhost:9093/")
public interface FareProxy {
	@PostMapping("/setFlightSeat")
	public Flight setFlightSeat(@RequestBody Flight flight);
	
	@PostMapping("/setFareForEachSeat")
	public Schedule setFareForEachSeat(@RequestBody Schedule schedule);
}
