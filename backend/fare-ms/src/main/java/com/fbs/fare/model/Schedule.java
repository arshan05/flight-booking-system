package com.fbs.fare.model;


import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "schedules")
public class Schedule {
	@Id
    private String id;
	@DBRef
	private Flight flight;
	@DBRef
	private Airport boarding;
	@DBRef
	private Airport destination;
	private Date startTime;
	private Date endTime;
	private Status scheduleStatus;
	private List<FlightSeat> fare;
	private int baseFare;
	
}
