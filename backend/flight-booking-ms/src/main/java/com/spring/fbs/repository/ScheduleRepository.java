package com.spring.fbs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.fbs.model.Schedule;

public interface ScheduleRepository extends MongoRepository<Schedule, String>{

}
