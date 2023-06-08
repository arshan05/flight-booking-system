package com.fbs.airline.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
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
}
