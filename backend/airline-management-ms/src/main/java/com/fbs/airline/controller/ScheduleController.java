package com.fbs.airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fbs.airline.exception.ScheduleException;
import com.fbs.airline.model.Schedule;
import com.fbs.airline.service.ScheduleService;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

	@Autowired
	ScheduleService scheduleService;

	
	//Schedules
	
	@PostMapping("/addSchedule")
	public Schedule addSchedule(@RequestBody Schedule schedule) throws ScheduleException {
		return scheduleService.addSchedule(schedule);
	}
	
	@GetMapping("/getAllSchedules")
	public List<Schedule> getAllSchedules() throws ScheduleException{
		return scheduleService.getAllSchedules();
	}

	@DeleteMapping("/deleteSchedule")
	public boolean deleteSchedule(@RequestBody Schedule schedule) throws ScheduleException {
		return scheduleService.deleteSchedule(schedule);
	}
	
	@PutMapping("/updateSchedule")
	public Schedule updateSchedule(@RequestBody Schedule schedule) throws ScheduleException {
		return scheduleService.updateSchedule(schedule);
	}
	
	
	
}
