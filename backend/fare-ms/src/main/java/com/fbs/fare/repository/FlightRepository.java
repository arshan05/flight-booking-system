package com.fbs.fare.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fbs.fare.model.Flight;

public interface FlightRepository extends MongoRepository<Flight, String>{
}
