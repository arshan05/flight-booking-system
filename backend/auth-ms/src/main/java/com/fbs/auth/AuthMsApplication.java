package com.fbs.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.fbs.auth")
public class AuthMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthMsApplication.class, args);
	}

}
