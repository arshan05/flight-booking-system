package com.fbs.customer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "passengers")
public class Passenger {
	@Id
    private String id;
	
	private String name;
	private String email;
	private long phoneNumber;
}
