package com.fbs.airline.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.fbs.airline.model.Airline;

public interface AirlineRepository extends MongoRepository<Airline, String>{

	boolean existsByAirlineName(String airlineName);

}
