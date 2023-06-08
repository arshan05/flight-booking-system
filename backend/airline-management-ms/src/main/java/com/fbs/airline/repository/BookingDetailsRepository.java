package com.fbs.airline.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.fbs.airline.model.BookingDetails;

public interface BookingDetailsRepository extends MongoRepository<BookingDetails, String> {

}
