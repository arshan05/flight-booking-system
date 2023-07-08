import { Button, Typography } from "@mui/material";
import axios from "axios";
import { useEffect } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";

const API_BASE_URL = "http://localhost:9092/api/consumer";

const Success = () => {
  const navigate = useNavigate();
  
  const bookedSchedule = useSelector((state) => state.bookedSchedule);

  console.log(bookedSchedule);
  const bookFlightRequest = {
    schedule: bookedSchedule.bookedSchedule,
    passenger: bookedSchedule.passenger,
    seatNumber: bookedSchedule.seatNumber,
  };
  console.log(bookFlightRequest);

  //   const handler = async () => {
  useEffect(() => {
    const bookFlight = async () => {
      try {
        const response = await axios.post(
          `${API_BASE_URL}/hello`,
          bookFlightRequest,
          {
            withCredentials: true,
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
        onClick={()=>{navigate('/')}}
      >
        Go to Home
      </Button>
    </div>
  );
};

export default Success;
