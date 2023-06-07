package com.spring.fbs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.fbs.model.Airline;

public interface AirlineRepository extends MongoRepository<Airline, String>{

}
