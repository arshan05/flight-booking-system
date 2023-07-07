import axios from "axios";
import { useEffect } from "react";
import { useSelector } from "react-redux";

const API_BASE_URL = "http://localhost:9092/api/consumer";

const Success = () => {
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

  return <div>success</div>;
};

export default Success;
