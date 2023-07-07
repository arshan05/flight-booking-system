package com.fbs.checkin.model;

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
	private Passenger passenger;

	@DBRef
	private Schedule schedule;
	
	private boolean isCheckedIn;
	private String PNR;
}
