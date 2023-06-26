package com.fbs.airline.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document(collection = "bookingDetails")
public class BookingDetails {
	
	@Id
	private String id;
	@DBRef
	private Flight flight;
	
	@DBRef
	private Passenger passenger;
	private Location startLocation;
	private Location endLocation;
	private Date boardingDate;
	private String seatNumber;
	private String PNR;
	private boolean isCheckedIn;
}
