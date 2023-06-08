package com.fbs.airline.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.fbs.airline.model.Airport;

public interface AirportRepository extends MongoRepository<Airport, String>{

}
