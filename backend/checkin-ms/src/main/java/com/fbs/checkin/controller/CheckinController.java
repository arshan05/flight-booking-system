package com.fbs.checkin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fbs.checkin.exception.BookingDetailsException;
import com.fbs.checkin.model.BookingDetails;
import com.fbs.checkin.service.AuthService;
import com.fbs.checkin.service.CheckinService;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api/checkin")
public class CheckinController {

	@Autowired
	CheckinService checkinService;

	@Autowired
	AuthService authService;

	@PostMapping("/passenger/{pnr}")
	public ResponseEntity<BookingDetails> checkInPassenger(@RequestHeader("cookie") String cookie,
			@PathVariable String pnr) {
		try {
			if (authService.isSessionValid(cookie)) {

				return ResponseEntity.ok(checkinService.checkInPassenger(pnr));
			} else {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
			}
		} catch (

		Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}

	}

	@PostMapping("/passenger2")
	public BookingDetails hello(@RequestBody String pnr) throws BookingDetailsException {
		return checkinService.checkInPassenger(pnr);
	}
}
