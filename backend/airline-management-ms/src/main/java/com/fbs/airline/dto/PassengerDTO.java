package com.fbs.airline.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PassengerDTO {
    private String id;
	private String name;
	private String email;
	private long phoneNumber;
	private List<BookingDetailsDTO> bookingDetails;	
}
