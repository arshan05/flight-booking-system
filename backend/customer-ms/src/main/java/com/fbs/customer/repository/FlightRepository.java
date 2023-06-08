package com.fbs.customer.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.fbs.customer.model.Flight;

public interface FlightRepository extends MongoRepository<Flight, String>{

}
