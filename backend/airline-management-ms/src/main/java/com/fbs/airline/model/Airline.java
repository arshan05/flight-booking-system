package com.fbs.airline.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "airlines")
public class Airline {
	@Id
    private String id;
	private String code;
	private String airlineName;
	
	@DBRef(lazy = true)
	@JsonIgnoreProperties("airlineCompany")
	private List<Flight> flights;
	
	
}
