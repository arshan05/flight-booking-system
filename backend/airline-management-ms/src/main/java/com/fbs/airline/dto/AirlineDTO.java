package com.fbs.airline.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AirlineDTO {
    private String id;
	private String code;
	private String airlineName;	
	private List<FlightDTO> flights;
	
	
}
