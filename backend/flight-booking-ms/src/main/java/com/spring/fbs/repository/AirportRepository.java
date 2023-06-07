package com.spring.fbs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.fbs.model.Airport;

public interface AirportRepository extends MongoRepository<Airport, String>{

}
