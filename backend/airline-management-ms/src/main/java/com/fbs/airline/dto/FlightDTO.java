package com.fbs.airline.dto;


import java.util.ArrayList;
import java.util.List;

import com.fbs.airline.model.Airline;
import com.fbs.airline.model.Schedule;
import com.fbs.airline.model.Seat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlightDTO {
    private String id;
	
	private String flightNumber;
	
	private Airline airlineCompany;
	private int seatCapacity;
	private int numberOfColumns;
	private List<Seat> seat;
	
	private List<Schedule> schedules = new ArrayList<>();

	
	
}

