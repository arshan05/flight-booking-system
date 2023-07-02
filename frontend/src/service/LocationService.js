import axios from "axios";
import { locationActions } from "../store/location-slice";

const API_BASE_URL = "http://localhost:7777/api/location";
export const getLocations = () => {
  return async (dispatch) => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`${API_BASE_URL}/getAllLocations`, {
          headers: {
            "Content-Type": "application/json",
          },
        });

        if (response.status != 200) {
          throw new Error("Could not fetch location data");
        }

        const data = response.data;
        return data;
      } catch (error) {
        throw new Error(error);
      }
    };

    const locationData = await fetchData();
    dispatch(locationActions.replaceLocations(locationData));
  };
};
