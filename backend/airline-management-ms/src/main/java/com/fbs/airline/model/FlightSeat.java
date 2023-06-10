package com.fbs.airline.model;

import lombok.Data;

@Data
public class FlightSeat extends Seat{
	private int price;
	private SeatStatus seatStatus;
	public FlightSeat(Seat seat) {
		super(seat.getSeatNumber(), seat.getClassName());
	}
	public FlightSeat(String seatNumber, String className) {
		super(seatNumber, className);
	}
	
	
	
}
