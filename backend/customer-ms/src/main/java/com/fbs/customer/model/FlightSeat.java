package com.fbs.customer.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FlightSeat extends Seat {
	private int price;
	private SeatStatus seatStatus;
	
	public FlightSeat(String seatNumber, String className, int price, SeatStatus status) {
		super(seatNumber,className);
		this.price = price;
		this.seatStatus = status;
	}

}
