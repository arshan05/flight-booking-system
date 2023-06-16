package com.fbs.airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fbs.airline.exception.ScheduleException;
import com.fbs.airline.model.Schedule;
import com.fbs.airline.service.AuthService;
import com.fbs.airline.service.ScheduleService;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

	@Autowired
	ScheduleService ScheduleService;

	@Autowired
	AuthService authService;

	// Schedules

	@PostMapping("/addSchedule")
	public ResponseEntity<Schedule> addSchedule(@RequestHeader("cookie") String cookie, @RequestBody Schedule schedule)
			throws ScheduleException {
		try {
			if (authService.isSessionValid(cookie)) {
				Schedule newlyAddedSchedule = ScheduleService.addSchedule(schedule);
				return ResponseEntity.ok(newlyAddedSchedule);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}

	}

	@GetMapping("/getAllSchedules")
	public ResponseEntity<List<Schedule>> getAllSchedules(@RequestHeader("cookie") String cookie)
			throws ScheduleException {
		try {
			if (authService.isSessionValid(cookie)) {
				return ResponseEntity.ok(ScheduleService.getAllSchedules());
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}

	}

	@DeleteMapping("/deleteSchedule")
	public ResponseEntity<String> deleteAirine(@RequestHeader("cookie") String cookie, @RequestBody Schedule schedule)
			throws ScheduleException {
		try {
			if (authService.isSessionValid(cookie)) {
				ScheduleService.deleteSchedule(schedule);
				return ResponseEntity.ok("Successfully Deleted");
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}

	}

	@PutMapping("/updateSchedule")
	public ResponseEntity<Schedule> updateSchedule(@RequestHeader("cookie") String cookie, @RequestBody Schedule schedule)
			throws ScheduleException {
		try {
			if (authService.isSessionValid(cookie)) {
				return ResponseEntity.ok(ScheduleService.updateSchedule(schedule));
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}

	}
	
	
	
}
