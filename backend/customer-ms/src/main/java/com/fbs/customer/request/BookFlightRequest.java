package com.fbs.customer.request;

import com.fbs.customer.model.Passenger;
import com.fbs.customer.model.Schedule;

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
public class BookFlightRequest {
	private Schedule schedule;
	private Passenger passenger;
	private String seatNumber;
	
}
