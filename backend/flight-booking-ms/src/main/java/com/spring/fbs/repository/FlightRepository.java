package com.spring.fbs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.fbs.model.Flight;

public interface FlightRepository extends MongoRepository<Flight, String>{

}
