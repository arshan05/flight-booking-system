package com.fbs.auth.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fbs.auth.jwt.AuthTokenUtility;
import com.fbs.auth.model.User;
import com.fbs.auth.repository.UsersRepository;
import com.fbs.auth.request.LoginRequest;
import com.fbs.auth.response.AuthenticationResponse;
import com.fbs.auth.response.MessageResponse;
import com.fbs.auth.response.UserInfoResponse;
import com.fbs.auth.service.UserDetailsImpl;
import com.fbs.auth.service.UserDetailsServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:3000")
public class AuthController {

	Logger log = LoggerFactory.getLogger(AuthController.class);

	public AuthController() {
		super();
		System.out.println("===============inside authcontroller===============");
	}

	@Autowired
	private AuthTokenUtility jwtTokenUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UsersRepository usersRepository;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		ResponseCookie jwtCookie = jwtTokenUtil.generateJwtCookie(userDetails);
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		log.info("========== jwt cookie" + jwtCookie.toString() + "===========");

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.SET_COOKIE, jwtCookie.toString());
		return ResponseEntity.ok().headers(headers).body(new UserInfoResponse(userDetails.getUsername(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody User user) {
		if (usersRepository.existsByUsername(user.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@GetMapping("/validate")
	public ResponseEntity<AuthenticationResponse> validate(@RequestHeader("cookie") String cookie) {
		int startIndex = cookie.indexOf("=") + 1;
		int endIndex = cookie.indexOf(";");
		String token;
		if(endIndex != -1)
		 token = cookie.substring(startIndex,endIndex);
		else
			token = cookie.substring(startIndex);
		
		System.out.println("token " + token);
		AuthenticationResponse response = new AuthenticationResponse();
		try {
			if (token == null) {
				response.setValid(false);
				return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
			} else {

				if (jwtTokenUtil.validateJwtToken(token)) {

					String username = jwtTokenUtil.getUsernameFromToken(token);
					UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

					List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
							.collect(Collectors.toList());

					response.setName(username);
					response.setRole(roles);
					response.setValid(true);
				} else {
					response.setValid(false);
					return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}
