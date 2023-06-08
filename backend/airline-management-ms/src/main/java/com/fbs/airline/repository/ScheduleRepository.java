package com.fbs.airline.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.fbs.airline.model.Schedule;

public interface ScheduleRepository extends MongoRepository<Schedule, String>{

}
