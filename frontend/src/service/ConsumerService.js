import axios from "axios";
import { resultActions } from "../store/result-slice";
import { useSelector } from "react-redux";

const API_BASE_URL = "http://localhost:7777/api/consumer";
export const getFlights = (flightDetails) => {
  return async (dispatch) => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`${API_BASE_URL}/getFlights`, {
          params: flightDetails,
          withCredentials: true,
          headers: {
            "Content-Type": "application/json",
          },
        });

        if (response.status != 200) {
          throw new Error("Could not fetch flights data");
        }

        if (response.data.length === 0) {
          dispatch(resultActions.replaceScheduleResult({}));
          throw new Error("flights are not available for this route and date");
        }

        const data = response.data;
        return data;
      } catch (error) {
        console.log(error);
      }
    };

    const schedulesData = await fetchData();
    if (schedulesData === undefined) {
      console.log("Results not found");
      dispatch(resultActions.replaceScheduleResult({}));
    } else {
      dispatch(resultActions.replaceScheduleResult(schedulesData));
    }
  };
};
