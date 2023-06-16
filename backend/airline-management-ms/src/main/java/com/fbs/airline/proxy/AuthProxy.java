package com.fbs.airline.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.fbs.airline.model.AuthenticationResponse;
import com.fbs.airline.model.LoginRequest;
import com.fbs.airline.model.User;

import jakarta.validation.Valid;

@FeignClient(name="auth-service", url="http://localhost:9098/api/auth")
public interface AuthProxy {
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest);
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody User user);
	
	@GetMapping("/validate")
	public ResponseEntity<AuthenticationResponse> validate(@RequestHeader("cookie") String cookie);
}
