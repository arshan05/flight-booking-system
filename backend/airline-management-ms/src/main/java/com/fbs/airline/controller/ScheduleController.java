package com.fbs.airline.controller;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.fbs.airline.service.IScheduleService;

@RestController
@RequestMapping("/api/schedule")
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
public class ScheduleController {

	private static final String YOU_ARE_UNAUTHORIZED = "You are Unauthorized!...";

	@Autowired
	IScheduleService scheduleService;

	@Autowired
	AuthService authService;

	// Schedules

	@PostMapping("/addSchedule")
	public ResponseEntity<Schedule> addSchedule(@RequestHeader("cookie") String cookie, @RequestBody Schedule schedule)
			throws ScheduleException {
		try {
			if (authService.isSessionValid(cookie)) {
				Schedule newlyAddedSchedule = scheduleService.addSchedule(schedule);
				return ResponseEntity.ok(newlyAddedSchedule);
			} else {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);

			}

		} catch (ScheduleException e) {
			throw new ScheduleException(e.getMessage());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		}

	}

	@GetMapping("/getAllSchedules")
	public ResponseEntity<List<Schedule>> getAllSchedules(@RequestHeader("cookie") String cookie)
			throws ScheduleException {
		try {
			if (authService.isSessionValid(cookie)) {
				return ResponseEntity.ok(scheduleService.getAllSchedules());
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		}

	}

//	@GetMapping("/getAllSchedules")
//	public ResponseEntity<List<Schedule>> getAllSchedules()
//			throws ScheduleException {
//		return ResponseEntity.ok(scheduleService.getAllSchedules());	
//
//	}

	@DeleteMapping("/deleteSchedule")
	public ResponseEntity<String> deleteAirine(@RequestHeader("cookie") String cookie, @RequestBody Schedule schedule)
			throws ScheduleException {
		try {
			if (authService.isSessionValid(cookie)) {
				scheduleService.deleteSchedule(schedule);
				return ResponseEntity.ok("Successfully Deleted");
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		}

	}

	@PutMapping("/updateSchedule")
	public ResponseEntity<Schedule> updateSchedule(@RequestHeader("cookie") String cookie,
			@RequestBody Schedule schedule) throws ScheduleException {
		try {
			if (authService.isSessionValid(cookie)) {
				Date istDateStart = schedule.getStartTime();
				Date utcDateStart = new Date(istDateStart.getTime() + TimeZone.getTimeZone("UTC").getOffset(istDateStart.getTime()));
				
				Date istDateEnd = schedule.getEndTime();
				Date utcDateEnd = new Date(istDateEnd.getTime() + TimeZone.getTimeZone("UTC").getOffset(istDateEnd.getTime()));
				
				System.out.println("start: " + istDateStart + " " + utcDateStart);
				System.out.println("end: " + istDateEnd + " " + utcDateEnd);
				
				return ResponseEntity.ok(scheduleService.updateSchedule(schedule));
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, YOU_ARE_UNAUTHORIZED);
		}

	}

//	@GetMapping("/hello")
//	public String hello() {
//		return "hello";
//	}

}
