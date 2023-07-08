import axios from "axios";
import { useSelector } from "react-redux";
import { allBookingsActions } from "../store/all-bookings-slice";

const API_BASE_URL = "http://localhost:7777/api/consumer";
export const getAllBookings = (email) => {
  return async (dispatch) => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          `${API_BASE_URL}/getBookingDetailsByEmail/${email}`,
          {
            withCredentials: true,
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        if (response.status != 200) {
          throw new Error("Could not fetch bookings data");
        }

        const data = response.data;
        console.log(data);
        return data;
      } catch (error) {
        throw new Error(error);
      }
    };

    const bookingData = await fetchData();
    dispatch(allBookingsActions.replaceBookings(bookingData));
  };
};

export const cancelTicket = (bookingDetails) => {
  return async (dispatch) => {
    try {
      const response = await axios.post(
        `http://localhost:9092/api/consumer/cancelTicket`,
        bookingDetails,
        {
          withCredentials: true,
        }
      );

      const data = response.data;
      console.log(data);
      dispatch(allBookingsActions.deleteBooking(bookingDetails));
    } catch (error) {
      console.log(error);
    }
  };
};

export const checkinPassenger = (pnr) => {
  return async (dispatch) => {
    try {
      const response = await axios.post(
        `http://localhost:9094/api/checkin/passenger/${pnr}`,
        pnr,
        {
          withCredentials: true,
        }
      );

      const data = response.data;
      console.log(data);
      dispatch(allBookingsActions.updateBooking(data));
    } catch (error) {
      console.log(error);
    }
  };
};
