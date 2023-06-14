package com.fbs.fare.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.fbs.fare.model.Flight;

public interface FlightRepository extends MongoRepository<Flight, String>{
}
