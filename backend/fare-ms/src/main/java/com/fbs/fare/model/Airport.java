package com.fbs.fare.model;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "airports")
public class Airport {
	@Id
    private String id;
	private Location location;
	private String code;
	private String airportName;
	private List<Flight> flights;
}
