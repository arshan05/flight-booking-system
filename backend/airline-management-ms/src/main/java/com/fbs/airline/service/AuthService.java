package com.fbs.airline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fbs.airline.exception.AuthenticationResponseNullException;
import com.fbs.airline.model.AuthenticationResponse;
import com.fbs.airline.proxy.AuthProxy;

@Service
public class AuthService {
	@Autowired
	AuthProxy authProxy;

	public boolean isSessionValid(String cookie) {
		System.out.println("isValid");
		ResponseEntity<AuthenticationResponse> authenticationResponse = authProxy.validate(cookie);
		System.out.println("inside session Valid:"+ authenticationResponse);
		if (authenticationResponse == null) {
			throw new AuthenticationResponseNullException("Auth reponse returned as  NULL");
		}
		else {
			for (String role : authenticationResponse.getBody().getRole()) {
				System.out.println("inside session Valid: " + role);
				if (role.equalsIgnoreCase("admin"))
					return true;
			}
		}

		return false;

	}
}
