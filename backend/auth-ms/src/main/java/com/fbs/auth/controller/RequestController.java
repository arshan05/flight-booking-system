package com.fbs.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:9999/api/hello")
public class RequestController {
	
	@GetMapping("/hello")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String hello() {
		return "hello";
	}
}
