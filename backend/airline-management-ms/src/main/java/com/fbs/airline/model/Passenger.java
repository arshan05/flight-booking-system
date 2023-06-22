package com.fbs.airline.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
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
