package com.fbs.customer.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "flights")
public class Flight {
	@Id
    private String id;
	
	private String flightNumber;
	
	@DBRef(lazy = true)
	@JsonIgnoreProperties("flights")
	private Airline airlineCompany;
	private int seatCapacity;
	private int numberOfColumns;
	private List<Seat> seat;
	
	@DBRef(lazy =true)
	@JsonIgnoreProperties("flight")
	private List<Schedule> schedules = new ArrayList<>();

	
	
}

