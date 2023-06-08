package com.fbs.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fbs.customer.model.Airline;

public interface AirlineRepository extends MongoRepository<Airline, String>{

}
