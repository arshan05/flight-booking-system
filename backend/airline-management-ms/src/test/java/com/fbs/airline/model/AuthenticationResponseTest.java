package com.fbs.airline.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class AuthenticationResponseTest {

	@Test
	public void testAuthenticationResponse() {
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		authenticationResponse.setName("John Doe");
		authenticationResponse.setRole(Arrays.asList("admin"));
		authenticationResponse.setValid(true);

		assertThat(authenticationResponse.getName()).isEqualTo("John Doe");
		assertThat(authenticationResponse.getRole()).isEqualTo(Arrays.asList("admin"));
		assertThat(authenticationResponse.isValid()).isEqualTo(true);
	}

	@Test
	void testToString() {
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		authenticationResponse.setName("John Doe");
		authenticationResponse.setRole(Arrays.asList("admin"));
		authenticationResponse.setValid(true);

		String expectedString = "AuthenticationResponse(name=John Doe, role=[admin], isValid=true)";
		assertEquals(expectedString, authenticationResponse.toString());
	}

	@Test
	void testAllArgsConstructor() {
		AuthenticationResponse authenticationResponse = new AuthenticationResponse("John Doe", Arrays.asList("admin"), true);
		assertThat(authenticationResponse.getName()).isEqualTo("John Doe");
		assertThat(authenticationResponse.getRole()).isEqualTo(Arrays.asList("admin"));
		assertThat(authenticationResponse.isValid()).isEqualTo(true);
	}

	@Test
	void testNoArgsConstructor() {
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();

		assertNotNull(authenticationResponse);
	}
}
