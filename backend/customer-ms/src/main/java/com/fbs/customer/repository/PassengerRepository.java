package com.fbs.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fbs.customer.model.Passenger;

public interface PassengerRepository extends MongoRepository<Passenger, String>{

}
