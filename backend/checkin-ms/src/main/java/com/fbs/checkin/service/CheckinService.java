package com.fbs.checkin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbs.checkin.exception.BookingDetailsException;
import com.fbs.checkin.model.BookingDetails;
import com.fbs.checkin.repository.BookingDetailsRepository;

@Service
public class CheckinService {

	@Autowired
	BookingDetailsRepository bookingDetailsRepository;

	public BookingDetails checkInPassenger(String pnr) throws BookingDetailsException {
		if (bookingDetailsRepository.existsByPNR(pnr)) {
			BookingDetails bookingDetails = bookingDetailsRepository.findByPNR(pnr);
			bookingDetails.setCheckedIn(true);
			bookingDetailsRepository.save(bookingDetails);
			return bookingDetails;
		}
		else {
			throw new BookingDetailsException("Invalid PNR Number");
		}
	}

}
