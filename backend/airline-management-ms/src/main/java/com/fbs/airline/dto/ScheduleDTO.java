package com.fbs.airline.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScheduleDTO {
    private String id;
	private FlightDTO flight;
	private AirportDTO boarding;
	private AirportDTO destination;
	private Date startTime;
	private Date endTime;
	private StatusDTO scheduleStatus;
	private List<FlightSeatDTO> fare;
	private int baseFare;
	
}