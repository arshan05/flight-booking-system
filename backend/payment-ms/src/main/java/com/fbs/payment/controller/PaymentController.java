package com.fbs.payment.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fbs.payment.order.Order;
import com.fbs.payment.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {
	
	private static final String CANCEL = "http://localhost:3000/cancel";
	private static final String SUCCESS_URL = "http://localhost:3000/success";
	@Autowired
	PaypalService paypalService;
	
	
	@PostMapping(value="/makePayment/{registartionId}",produces = "application/json")
	public String makePayment(@RequestBody Order order,@PathVariable String registartionId) throws IOException {
		try {
			Payment payment = paypalService.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
					order.getIntent(), order.getDescription(), CANCEL,
					SUCCESS_URL);
			for(Links link:payment.getLinks()) {
				if(link.getRel().equals("approval_url")) {
					
					String redirectUrl = UriComponentsBuilder.fromUriString(link.getHref())
	                        .build()
	                        .toUriString();
	                return "redirect:" + redirectUrl;
				}
			}
			
		} catch (PayPalRESTException e) {
		
			e.printStackTrace();
		}	 	  	  	    	      	        	 	
		return "sign";
		
	}
}
