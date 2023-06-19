package com.fbs.checkin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fbs.checkin.model.BookingDetails;

public interface BookingDetailsRepository extends MongoRepository<BookingDetails, String>{

	boolean existsByPNR(String pnr);

	BookingDetails findByPNR(String pnr);

}
