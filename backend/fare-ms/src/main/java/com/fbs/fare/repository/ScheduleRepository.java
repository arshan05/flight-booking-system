package com.fbs.fare.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fbs.fare.model.Flight;
import com.fbs.fare.model.Schedule;
import java.util.List;


public interface ScheduleRepository extends MongoRepository<Schedule, String>{
	
}
