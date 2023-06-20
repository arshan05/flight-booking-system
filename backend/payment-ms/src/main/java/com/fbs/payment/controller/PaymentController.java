package com.fbs.payment.controller;

import java.math.BigInteger;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fbs.payment.order.OrderRequest;
import com.fbs.payment.order.OrderResponse;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/payment")
@CrossOrigin("http://localhost:3000/")
public class PaymentController {
	
	public PaymentController() {
		System.out.println("hello controller");
	}

	private RazorpayClient client;
//	@Value("$SECRET_ID")
//	private String SECRET_ID;
//	
//	@Value("$SECRET_KEY")
//	private String SECRET_KEY;

	@Value("${SECRET_KEY}")
	private String SECRET_KEY;

	@Value("${SECRET_ID}")
	private String SECRET_ID;
	
	
	@PostMapping("/createOrder")
	public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
		OrderResponse response = new OrderResponse();
		try {
			System.out.println(SECRET_ID + " " + SECRET_KEY);
			client = new RazorpayClient(SECRET_ID, SECRET_KEY);
			
			Order order = createRazorPayOrder(orderRequest.getAmount());
			System.out.println("---------------------------");
			String orderId = (String) order.get("id");
			System.out.println("Order ID: " + orderId);
			System.out.println("---------------------------");
			response.setRazorpayOrderId(orderId);
			response.setApplicationFee("" + orderRequest.getAmount());
			
			response.setSecretKey(SECRET_KEY);
			response.setSecretId(SECRET_ID);
			response.setPgName("razor");


			return response;
		} catch (RazorpayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;

	}

	private Order createRazorPayOrder(BigInteger amount) throws RazorpayException {

		JSONObject options = new JSONObject();
		options.put("amount", amount.multiply(new BigInteger("100")));
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		options.put("payment_capture", 1); // You can enable this if you want to do Auto Capture.
		return client.orders.create(options);
	}
}
