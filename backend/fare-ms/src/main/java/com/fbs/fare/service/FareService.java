package com.fbs.fare.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbs.fare.model.Flight;
import com.fbs.fare.model.FlightSeat;
import com.fbs.fare.model.Schedule;
import com.fbs.fare.model.Seat;
import com.fbs.fare.model.SeatStatus;
import com.fbs.fare.repository.FlightRepository;
import com.fbs.fare.repository.ScheduleRepository;

@Service
public class FareService {
	
	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Autowired
	FlightRepository flightRepository;

	public Schedule setFareForEachSeat(Schedule schedule) {
		// TODO Auto-generated method stub

		List<FlightSeat> seatFares = new ArrayList<>();
		int baseFare = schedule.getBaseFare();
		String id = schedule.getFlight().getId();
		Flight flight = flightRepository.findById(id).orElse(null);
		List<Seat> seats = flight.getSeat();
		
		for (Seat seat : seats) {
			int price = calculateFare(baseFare, seat);
			FlightSeat flightSeat = new FlightSeat(seat);
			flightSeat.setSeatStatus(SeatStatus.OPEN);
			flightSeat.setPrice(price);
			seatFares.add(flightSeat);
//			System.out.println(flightSeat.getSeatNumber());
		}
		schedule.setFare(seatFares);
		return schedule;
	}

	private int calculateFare(int baseFare, Seat seat) {
		// TODO Auto-generated method stub
		String seatNumber = seat.getSeatNumber();
		String className = seat.getClassName();

		double multiplier = 1;
		char seatPosition = seatNumber.charAt(seatNumber.length() - 1);
		if (className.equalsIgnoreCase("business")) {
			switch (seatPosition) {
			case 'A':
			case 'F':
				multiplier = 1.5;
				break;
			case 'B':
			case 'E':
				multiplier = 1.3;
				break;
			case 'C':
			case 'D':
				multiplier = 1.4;
				break;
			default:
				break;
			}
		} else {

			switch (seatPosition) {
			case 'A':
			case 'F':
				multiplier = 1.3;
				break;
			case 'B':
			case 'E':
				multiplier = 1;
				break;
			case 'C':
			case 'D':
				multiplier = 1.2;
				break;
			default:
				break;
			}
		}

		return (int) (baseFare*multiplier);
	}

	public Flight setFlightSeat(Flight flight) {
		// TODO Auto-generated method stub

		List<Seat> seats = new ArrayList<>();

		int numberOfSeats = flight.getSeatCapacity();
		int numberOfColumns = flight.getNumberOfColumns();
		int numberOfRows = numberOfSeats / numberOfColumns;
		int businessRows = (int) ((int) numberOfRows * 0.2);

		for (int i = 1; i <= numberOfRows; i++) {
			for (int j = 1; j <= numberOfColumns; j++) {
				StringBuilder seatNumber = new StringBuilder();
				seatNumber.append(i + "" + (char) (64 + j));
				Seat seat;
				if (i <= businessRows) {
					seat = new Seat(seatNumber.toString(), "BUSINESS");
				} else {
					seat = new Seat(seatNumber.toString(), "ECONOMY");
				}
				seats.add(seat);

			}
		}

		flight.setSeat(seats);
		return flight;
	}

}
