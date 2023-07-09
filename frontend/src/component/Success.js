import { Button, Typography } from "@mui/material";
import axios from "axios";
import { useEffect } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";

const API_BASE_URL = "http://localhost:9092/api/consumer";

const Success = () => {
  const navigate = useNavigate();

  const bookedSchedule = useSelector((state) => state.bookedSchedule);
  const passenger = useSelector((state) => state.passenger);

  console.log(bookedSchedule);
  console.log(passenger);
  const bookFlightRequest = {
    schedule: bookedSchedule.bookedSchedule,
    passenger: passenger,
    seatNumber: bookedSchedule.seatNumber,
  };

  //   const handler = async () => {
  useEffect(() => {
    const bookFlight = async () => {
      try {
        console.log(bookFlightRequest);

        const response = await axios.post(
          `http://localhost:9092/api/consumer/bookFlight`,
          bookFlightRequest,
          {
            withCredentials:true
          }

        );

        const data = response.data;
        console.log(data);

        // Handle the received data here
      } catch (error) {
        console.log(error);
      }
    };

    bookFlight();
  }, []);
  //   };

  return (
    <div className="container d-flex flex-column align-items-center justify-content-center h-100">
      <Typography variant="h4" component="h1" gutterBottom>
        Payment Successful!
      </Typography>
      <Typography variant="body1" gutterBottom>
        An email confirmation has been sent.
      </Typography>
      <Button
        variant="contained"
        color="primary"
        onClick={() => {
          navigate("/");
        }}
      >
        Go to Home
      </Button>
    </div>
  );
};

export default Success;
