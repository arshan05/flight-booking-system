package com.fbs.checkin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fbs.checkin.model.BookingDetails;
import java.util.List;


public interface BookingDetailsRepository extends MongoRepository<BookingDetails, String> {
    BookingDetails findByPNR(String pnr);

	boolean existsByPNR(String pnr);
}
