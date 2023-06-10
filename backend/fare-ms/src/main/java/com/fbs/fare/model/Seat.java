package com.fbs.fare.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Seat {
	public Seat() {
		// TODO Auto-generated constructor stub
	}
	private String seatNumber;
	private String className;
}
