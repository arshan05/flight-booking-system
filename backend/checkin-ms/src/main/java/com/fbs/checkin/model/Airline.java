package com.fbs.checkin.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
