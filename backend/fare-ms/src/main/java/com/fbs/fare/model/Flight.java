package com.fbs.fare.model;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "flights")
public class Flight {
	@Id
    private String id;
	
	private String flightNumber;
	@DBRef
	private Airline airlineCompany;
	private int seatCapacity;
	private int numberOfColumns;
	private List<Seat> seat;
	private List<Schedule> schedules = new ArrayList<>();

	
	
}

