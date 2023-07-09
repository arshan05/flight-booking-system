import axios from "axios";
import { useDispatch } from "react-redux";
import { passengerActions } from "../store/passenger-slice";

const API_BASE_URL = "http://localhost:9092/api/consumer";

const PassengerService = {
  addPassenger: (passengerData) => {
    return async (dispatch) => {
      try {
        const response = await axios.post(
          `${API_BASE_URL}/addPassenger`,
          passengerData,
          {
            withCredentials: true,
          }
        );

        const data = response.data;

          console.log("passenger: "+ passengerData.name);
          console.log("passenger response: "+ data.id + " " + data.name);

        dispatch(passengerActions.setPassenger(data));
      } catch (error) {
        console.log(error);
      }
    };
  },
};

export default PassengerService;
