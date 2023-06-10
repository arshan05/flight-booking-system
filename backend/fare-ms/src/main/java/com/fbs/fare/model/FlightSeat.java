package com.fbs.fare.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightSeat extends Seat{
	private int price;
	private SeatStatus seatStatus;
	
	public FlightSeat(Seat seat) {
		super(seat.getSeatNumber(), seat.getClassName());
	}

	public FlightSeat() {
		super();
	}

	
}
