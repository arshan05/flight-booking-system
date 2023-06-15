package com.fbs.customer.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fbs.customer.model.EmailRequest;

@FeignClient(name = "email", url = "http://localhost:9093/")
public interface EmailProxy{
	
	 @PostMapping("/sendEmail")
	    public void sendEmail(@RequestBody EmailRequest emailRequest);
}
