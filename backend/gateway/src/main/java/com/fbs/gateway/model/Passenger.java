package com.fbs.gateway.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
	@DBRef
	private List<BookingDetails> bookingDetails;	
}
