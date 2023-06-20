package com.fbs.payment.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

	String secretKey;
	String razorpayOrderId;
	String applicationFee;
	String secretId;
	String pgName;

}