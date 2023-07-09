import React, { useEffect, useState } from "react";
import paymentService from "../service/PaymentService";
import { Button } from "@mui/material";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPaperPlane } from "@fortawesome/free-solid-svg-icons";
import { Navigate, useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import PassengerService, { addPassenger } from "../service/PassengerService";
import { bookedScheduleSliceActions } from "../store/booked-schedule-slice";

const Payment = (props) => {
  const dispatch = useDispatch();
  const passenger = useSelector((state) => state.passenger);
  const auth = useSelector((state) => state.auth);

  const navigate = useNavigate();

  const bookedSchedule = useSelector((state) => state.bookedSchedule);

  const email = auth.email;
  const passengerName = passenger.name;
  const phoneNumber = String(passenger.phoneNumber)
    .replace(/\D/g, "")
    .slice(-10);

  const schedule = props.schedule;

  const [filled, setFilled] = useState(false);

  useEffect(() => {
    if (passengerName !== "" && phoneNumber !== "") {
      setFilled(true);
    } else {
      setFilled(false);
    }
  }, [passengerName, phoneNumber]);

  const amount = props.sharedState.sharedSeat.price;

  const paymentHandler = async () => {
    try {
      const order = {
        price: Number(amount),
        currency: "USD",
        method: "paypal",
        intent: "sale",
        description: "Payment for order",
      };

      const passengerData = {
        name: passengerName,
        email: email,
        phoneNumber: Number(phoneNumber),
      };

      console.log("passenger before: " + passenger.name);

      const dispatchPassengerResponse = dispatch(
        PassengerService.addPassenger(passengerData)
      );

      const passengerResponseAction = await dispatchPassengerResponse;

      const dispatchResponse = await dispatch(
        bookedScheduleSliceActions.setDetails({
          bookedSchedule: schedule,
          seatNumber: props.sharedState.sharedSeat.seatNumber,
          passenger: passenger, // Access the passenger data from the action payload
        })
      );

      console.log(dispatchResponse);
      // const dispatchPassengerResponse = await dispatch(PassengerService.addPassenger(passengerData));

      // const dispatchResponse = await dispatch(
      //   bookedScheduleSliceActions.setDetails({
      //     bookedSchedule: schedule,
      //     seatNumber: props.sharedState.sharedSeat.seatNumber,
      //     passenger: dispatchPassengerResponse.payload,
      //   })
      // );
      // console.log(passenger);
      // console.log(dispatchResponse.payload.passenger.id);
      await paymentService.makePayment(order, dispatchResponse,passenger);

      // navigate("/success");

      // Code to execute after all dispatch actions and payment complete
    } catch (error) {
      // Handle any errors
      console.log("Error occurred during payment:", error);
    }
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
            style={{ backgroundColor: "lightGray", color: "gray" }}
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
