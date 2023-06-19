package com.fbs.checkin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fbs.checkin.service.AuthService;
import com.fbs.checkin.service.CheckinService;

@RestController
@RequestMapping("/api/checkin")
public class CheckinController {
	
	@Autowired
	CheckinService checkinService;
	
	@Autowired
	AuthService authService;
	
	
	@PostMapping("/passenger")
	public ResponseEntity<String> checkInPassenger(@RequestHeader("cookie") String cookie, @RequestBody String pnr){
		try {
			if (authService.isSessionValid(cookie)) {
				checkinService.checkInPassenger(pnr);
				return ResponseEntity.ok("Checkin successfull");
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
		
	}
}
