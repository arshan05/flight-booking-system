package com.fbs.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fbs.customer.model.Schedule;

public interface ScheduleRepository extends MongoRepository<Schedule, String>{
}
