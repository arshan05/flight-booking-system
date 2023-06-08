package com.fbs.airline.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.fbs.airline.model.Passenger;

public interface PassengerRepository extends MongoRepository<Passenger, String>{

}
