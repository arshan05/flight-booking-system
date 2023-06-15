package com.fbs.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightSeat extends Seat{
	private int price;
	private SeatStatus seatStatus;
//	public FlightSeat(Seat seat) {
//		super(seat.getSeatNumber(), seat.getClassName());
//	}
//	public FlightSeat(String seatNumber, String className) {
//		super(seatNumber, className);
//	}
//	
	
	
	
	
}
