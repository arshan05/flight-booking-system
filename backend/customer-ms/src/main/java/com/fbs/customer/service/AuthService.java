package com.fbs.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fbs.customer.exception.AuthenticationResponseNullException;
import com.fbs.customer.model.AuthenticationResponse;
import com.fbs.customer.proxy.AuthProxy;

@Service
public class AuthService {
	@Autowired
	AuthProxy authProxy;

	public boolean isSessionValid(String cookie) {
		System.out.println("isValid");
		ResponseEntity<AuthenticationResponse> authenticationResponse = authProxy.validate(cookie);
		if (authenticationResponse == null) {
			throw new AuthenticationResponseNullException("Auth reponse returned as  NULL");
		}
		else {
			for (String role : authenticationResponse.getBody().getRole()) {
				System.out.println(role);
				if (role.equalsIgnoreCase("USER"))
					return true;
			}
		}

		return false;

	}
}

