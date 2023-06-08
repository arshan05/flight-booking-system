package com.fbs.airline.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.fbs.airline.model.Flight;

public interface FlightRepository extends MongoRepository<Flight, String>{

}
