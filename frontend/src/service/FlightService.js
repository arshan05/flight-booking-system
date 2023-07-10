import axios from "axios";
import { flightActions } from "../store/flight-slice";

const API_BASE_URL = "http://localhost:9091/api/flight";
export const getFlights = () => {
  return async (dispatch) => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`${API_BASE_URL}/getAllFlights`, {
          withCredentials:true,
          headers: {
            "Content-Type": "application/json",
          },
        });

        if (response.status != 200) {
          throw new Error("Could not fetch flight data");
        }

        const data = response.data;
        console.log(data);
        return data;
      } catch (error) {
        throw new Error(error);
      }
    };

    const flightData = await fetchData();
    dispatch(flightActions.replaceFlights(flightData));
  };
};

export const addFlight = (flight) => {
  return async (dispatch) => {
    const addData = async (flight) => {
      try {
        console.log(flight);
        const response = await axios.post(
          `${API_BASE_URL}/addFlight`,
          flight,
          {
            withCredentials: true,
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        if (response.status != 200) {
          throw new Error("Could not add flight data");
        }

        const data = response.data;
        console.log(data);
        return data;
      } catch (error) {
        throw new Error(error);
      }
    };

    const flightData = await addData(flight);
    dispatch(flightActions.addFlight(flightData));
  };
};

export const deleteFlight = (loc) => {
  return async (dispatch) => {
    const deleteData = async (loc) => {
      try {
        const response = await axios.delete(`${API_BASE_URL}/deleteFlight`, {
          data: loc,
          withCredentials: true,
          headers: {
            "Content-Type": "application/json",
          },
        });

        if (response.status != 200) {
          throw new Error("Could not delete flight data");
        }

        const data = response.data;
        console.log(data);
        return data;
      } catch (error) {
        throw new Error(error);
      }
    };

    const flightData = await deleteData(loc);
    if (flightData === "Successfully Deleted") {
      dispatch(flightActions.deleteFlight(loc));
    }
  };
};
export const updateFlight = (loc) => {
  return async (dispatch) => {
    const updateData = async (loc) => {
      try {
        const response = await axios.put(
          `${API_BASE_URL}/updateFlight`,
          loc,
          {
            withCredentials: true,
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        if (response.status !== 200) {
          throw new Error("Could not update flight data");
        }

        const data = response.data;
        console.log(data);
        return data;
      } catch (error) {
        throw new Error(error);
      }
    };

    try {
      const flightData = await updateData(loc);
      // Dispatch the appropriate action
      console.log(flightData);
    } catch (error) {
      console.error(error);
    }
  };
};
