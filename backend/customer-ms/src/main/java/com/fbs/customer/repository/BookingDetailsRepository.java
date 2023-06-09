package com.fbs.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fbs.customer.model.BookingDetails;

public interface BookingDetailsRepository extends MongoRepository<BookingDetails, String> {

	BookingDetails findByPNRAndPassenger_Email(String pnr, String email);

}
