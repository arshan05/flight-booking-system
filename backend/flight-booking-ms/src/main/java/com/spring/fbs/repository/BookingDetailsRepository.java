package com.spring.fbs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.fbs.model.BookingDetails;

public interface BookingDetailsRepository extends MongoRepository<BookingDetails, String>{

}
