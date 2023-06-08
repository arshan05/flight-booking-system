package com.fbs.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fbs.customer.model.Airport;

public interface AirportRepository extends MongoRepository<Airport, String>{

}
