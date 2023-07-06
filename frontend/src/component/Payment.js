import React, { useEffect, useState } from "react";
import paymentService from "../service/PaymentService";
import { Button } from "@mui/material";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPaperPlane } from "@fortawesome/free-solid-svg-icons";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";

const Payment = (props) => {
  const dispatch = useDispatch();
  const passenger = useSelector((state) => state.passenger);
  const auth = useSelector((state) => state.auth);

  const email = auth.email;
  const passengerName = passenger.name;
  const phoneNumber = passenger.phoneNumber;

  const [filled, setFilled] = useState(false);

  useEffect(() => {
    if (passengerName !== '' && phoneNumber !== '') {
      setFilled(true);
    } else {
      setFilled(false);
    }
  }, [passengerName, phoneNumber]);

  const amount = props.sharedState.sharedSeat.price;
  const paymentHandler = () => {
    const order = {
      price: Number(amount), // Set the price based on your requirements
      currency: "USD", // Set the currency based on your requirements
      method: "paypal", // Set the payment method based on your requirements
      intent: "sale", // Set the payment intent based on your requirements
      description: "Payment for order", // Set the payment description based on your requirements
    };
    paymentService.makePayment(order);
  };

  return (
    <div>
      {filled ? (
        <Button
          fullWidth
          className="item"
          variant="contained"
          style={{ backgroundColor: "#142c54" }}
          onClick={paymentHandler}
        >
          <span style={{ marginRight: 10 }}>
            <FontAwesomeIcon icon={faPaperPlane} />
          </span>
          Book Ticket
        </Button>
      ) : (
        <div>
          <Button
            disabled
            fullWidth
            className="item"
            variant="contained"
            style={{ backgroundColor: "#142c54" }}
          >
            <span style={{ marginRight: 10 }}>
              <FontAwesomeIcon icon={faPaperPlane} />
            </span>
            Book Ticket
          </Button>
          <p className="text-danger" style={{ fontWeight: "bold" }}>
            Fill all required fields!!!
          </p>
        </div>
      )}
    </div>
  );
};

export default Payment;
