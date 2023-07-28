package com.fbs.checkin.model;

import java.util.Date;
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
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "schedules")
public class Schedule {
	@Id
    private String id;
	@DBRef
	@JsonIgnoreProperties("schedules")
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