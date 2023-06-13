package com.fbs.airline.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AirportDTO {
    private String id;
	private LocationDTO location;
	private String airportName;
	private String code;
	private List<FlightDTO> flights;
}
