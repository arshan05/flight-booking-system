package com.fbs.airline.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationDTO {
	private String place;
	private String State;
	private String country;
}
