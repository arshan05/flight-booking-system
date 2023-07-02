package com.fbs.airline.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fbs.airline.model.Location;

public interface LocationRepository extends MongoRepository<Location, String> {
	void deleteById(String id);

	boolean existsByPlace(String place);

}
