package com.fbs.airline.dto;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingDetailsDTO {
	
	private FlightDTO flight;	
	private PassengerDTO passenger;
	private String startLocation;
	private String endLocation;
	private Date boardingDate;
	private String PNR;
}
