package com.spring.fbs.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "airports")
public class Airport {
	private String location;
	private String airportName;
	private List<Flight> flights;
}
