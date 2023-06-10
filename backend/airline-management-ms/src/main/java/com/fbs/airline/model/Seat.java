package com.fbs.airline.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Seat {
	private String seatNumber;
	private String className;
}

