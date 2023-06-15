package com.fbs.auth.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bookingDetails")
public class BookingDetails {
	
	@DBRef
	private Flight flight;
	
	@DBRef
	private Passenger passenger;
	private Location startLocation;
	private Location endLocation;
	private Date boardingDate;
	private String PNR;
}
