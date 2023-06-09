package com.fbs.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.fbs.customer")
public class CustomerMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerMsApplication.class, args);
	}

}
