package com.fbs.checkin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fbs.checkin.model.AuthenticationResponse;
import com.fbs.checkin.proxy.AuthProxy;

@Service
public class AuthService {
	@Autowired
	AuthProxy authProxy;

	public boolean isSessionValid(String cookie) {

		ResponseEntity<AuthenticationResponse> authenticationResponse = authProxy.validate(cookie);
		if (authenticationResponse == null) {
			throw new RuntimeException("Auth reponse returned as  NULL");
		}

		for (String role : authenticationResponse.getBody().getRole()) {
			if (role.equalsIgnoreCase("USER"))
				return true;
		}

		return false;

	}
}
