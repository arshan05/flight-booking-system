package com.spring.fbs.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collation = "schedules")
public class Schedule {
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
