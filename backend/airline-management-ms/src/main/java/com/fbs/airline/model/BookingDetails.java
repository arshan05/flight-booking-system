package com.fbs.airline.model;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "bookingDetails")
public class BookingDetails {
	
	@DBRef
	private Flight flight;
	
	@DBRef
	private Passenger passenger;
	private String startLocation;
	private String endLocation;
	private Date boardingDate;
	private String PNR;
}
