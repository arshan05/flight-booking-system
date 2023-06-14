package com.fbs.fare.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fbs.fare.model.Schedule;


public interface ScheduleRepository extends MongoRepository<Schedule, String>{
	
}
