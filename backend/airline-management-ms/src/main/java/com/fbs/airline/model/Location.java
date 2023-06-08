package com.fbs.airline.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {
	private String place;
	private String State;
	private String country;
}
