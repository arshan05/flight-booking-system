import React, { useState } from "react";
import paymentService from "../service/PaymentService";

const Payment = () => {
  const [paymentId, setPaymentId] = useState("");

  const handlePayment = async () => {
    try {
      const orderRequest = {
        customerName: "Darshan",
        email: "darundarshan1223@gmail.com",
        amount: 1, // Replace with your actual amount
      };

      const paymentData = await paymentService.createRazorpayOrder(
        orderRequest
      );

      setPaymentId(paymentData.razorpayOrderId);

      const options = {
        key: paymentData.secretId,
        amount: paymentData.applicationFee,
        currency: 'INR',
        name: 'DARSHAN',
        description: 'Purchase Description',
        order_id: paymentData.razorpayOrderId,
        handler: (response) => {
          // Handle the payment success or failure
          console.log(response);
        },
        prefill: {
          email: orderRequest.email,
          contact: '8431877426',
        },
      };
  
      const razorpay = new window.Razorpay(options);
      razorpay.open();
    } catch (error) {
      console.error("Error creating Razorpay order:", error.message);
    }
  };

  return (
    <div>
      <button onClick={handlePayment}>Pay with Razorpay</button>
    </div>
  );
};

export default Payment;
