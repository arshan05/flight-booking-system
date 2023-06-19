package com.fbs.checkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.fbs.checkin")
public class CheckinMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckinMsApplication.class, args);
	}

}
