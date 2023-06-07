package com.spring.fbs.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "users")
public class User {
	private String name;
	private String email;
	private long phoneNumber;
	private String country;
	@DBRef
	private List<BookingDetails> bookingDetails;	
}
