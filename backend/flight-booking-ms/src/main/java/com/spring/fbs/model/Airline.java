package com.spring.fbs.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "airlines")
public class Airline {
	@Id
    private String id;
	
	private String airlineName;
//	@DBRef
	private List<Flight> flights;
	
//	public Airline(String airlineName) {
//		super();
//		this.airlineName = airlineName;
//	}
	
	
}
