package com.fbs.customer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class EmailRequestTest {
	
	@Test
	void testConstructorAndGetters() {
		EmailRequest emailRequest = new EmailRequest("to.example.com","subject","body");

		assertEquals("to.example.com", emailRequest.getTo());
		assertEquals("subject", emailRequest.getSubject());
		assertEquals("body", emailRequest.getBody());
		
	}

	@Test
	void testSetters() {
		EmailRequest emailRequest = new EmailRequest();

		emailRequest.setTo("to.example.com");
		emailRequest.setSubject("subject");
		emailRequest.setBody("body");

		assertEquals("to.example.com", emailRequest.getTo());
		assertEquals("subject", emailRequest.getSubject());
		assertEquals("body", emailRequest.getBody());
	}

	@Test
	void testToString() {
		EmailRequest emailRequest = new EmailRequest("to.example.com","subject","body");

		String expectedString = "EmailRequest(to=to.example.com, subject=subject, body=body)";
		assertEquals(expectedString, emailRequest.toString());
	}

	@Test
	void testAllArgsConstructor() {
		EmailRequest emailRequest = new EmailRequest("to.example.com","subject","body");

		assertEquals("to.example.com", emailRequest.getTo());
		assertEquals("subject", emailRequest.getSubject());
		assertEquals("body", emailRequest.getBody());
	}

	@Test
	void testNoArgsConstructor() {
		EmailRequest emailRequest = new EmailRequest();

		assertNotNull(emailRequest);
	}
}
