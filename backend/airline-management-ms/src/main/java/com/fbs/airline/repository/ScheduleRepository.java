package com.fbs.airline.repository;

import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fbs.airline.model.Schedule;

public interface ScheduleRepository extends MongoRepository<Schedule, String>{

//	boolean existsByStartTimeBetweenOrEndTimeBetween(Date startTime, Date endTime);
	boolean existsByStartTimeBetweenOrEndTimeBetween(Date startTime, Date endTime, Date startTime2, Date endTime2);

}
