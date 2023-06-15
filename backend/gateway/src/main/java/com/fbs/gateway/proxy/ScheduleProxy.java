package com.fbs.gateway.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fbs.gateway.exception.ScheduleException;
import com.fbs.gateway.model.Schedule;

@FeignClient(name = "schedule", url = "http://localhost:9091/api/schedule")
public interface ScheduleProxy {
	@PostMapping("/addSchedule")
	public ResponseEntity<Schedule> addSchedule(@RequestBody Schedule Schedule) throws ScheduleException;

	@GetMapping("/getAllSchedules")
	public ResponseEntity<List<Schedule>> getAllSchedules() throws ScheduleException;

	@DeleteMapping("/deleteSchedule")
	public ResponseEntity<String> deleteAirine(@RequestBody Schedule Schedule) throws ScheduleException;

	@PutMapping("/updateSchedule")
	public ResponseEntity<Schedule>  updateSchedule(@RequestBody Schedule Schedule) throws ScheduleException;
}
