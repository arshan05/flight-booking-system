package com.fbs.airline.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightSeatDTO extends SeatDTO{
	private int price;
	private SeatStatusDTO seatStatus;	
}
