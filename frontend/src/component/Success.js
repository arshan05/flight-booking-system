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
    <div className="mt-5 container">
      <div className="row d-flex justify-content-center">
        <div className="col-md-6">
          <img
            src="https://cdn.dribbble.com/users/1583708/screenshots/17285458/media/dd08ec8d00a19dd0aaaea49a3fd761f1.png?resize=1000x750&vertical=center"
            alt="Payment Success"
            className="img-thumbnail"
          />
        </div>
        <div className="col-md-6 text-center d-flex align-items-center">
          <div>
            <h2 className="mb-4">Payment Successful!</h2>
            <p>
              Thank you for your payment. Your transaction has been completed
              successfully.
            </p>
            <p>
              A confirmation email has been sent to your registered email
              address.
            </p>
            {navigate && (
              <button
                className="btn btn-primary"
                onClick={() => {
                  navigate("/");
                }}
              >
                Go to Home
              </button>
            )}
          </div>
        </div>
      </div>
    </div>
    // <div className="container d-flex flex-column align-items-center justify-content-center h-100">
    //   <Typography variant="h4" component="h1" gutterBottom>
    //     Payment Successful!
    //   </Typography>
    //   <Typography variant="body1" gutterBottom>
    //     An email confirmation has been sent.
    //   </Typography>
    //   <Button
    //     variant="contained"
    //     color="primary"
    //     onClick={() => {
    //       navigate("/");
    //     }}
    //   >
    //     Go to Home
    //   </Button>
    // </div>
  );
};

export default Success;
