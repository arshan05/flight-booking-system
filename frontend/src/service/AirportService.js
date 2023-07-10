import axios from "axios";
import { airportActions } from "../store/airport-slice";

const API_BASE_URL = "http://localhost:9091/api/airport";
export const getAirports = () => {
  return async (dispatch) => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`${API_BASE_URL}/getAllAirports`, {
          withCredentials:true,
          headers: {
            "Content-Type": "application/json",
          },
        });

        if (response.status != 200) {
          throw new Error("Could not fetch airport data");
        }

        const data = response.data;
        console.log(data);
        return data;
      } catch (error) {
        throw new Error(error);
      }
    };

    const airportData = await fetchData();
    dispatch(airportActions.replaceAirports(airportData));
  };
};

export const addAirport = (airport) => {
  return async (dispatch) => {
    const addData = async (airport) => {
      try {
        console.log(airport);
        const response = await axios.post(
          `${API_BASE_URL}/addAirport`,
          airport,
          {
            withCredentials: true,
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        if (response.status != 200) {
          throw new Error("Could not add airport data");
        }

        const data = response.data;
        console.log(data);
        return data;
      } catch (error) {
        throw new Error(error);
      }
    };

    const airportData = await addData(airport);
    dispatch(airportActions.addAirport(airportData));
  };
};

export const deleteAirport = (loc) => {
  return async (dispatch) => {
    const deleteData = async (loc) => {
      try {
        const response = await axios.delete(`${API_BASE_URL}/deleteAirport`, {
          data: loc,
          withCredentials: true,
          headers: {
            "Content-Type": "application/json",
          },
        });

        if (response.status != 200) {
          throw new Error("Could not delete airport data");
        }

        const data = response.data;
        console.log(data);
        return data;
      } catch (error) {
        throw new Error(error);
      }
    };

    const airportData = await deleteData(loc);
    if (airportData === "Successfully Deleted") {
      dispatch(airportActions.deleteAirport(loc));
    }
  };
};
export const updateAirport = (loc) => {
  return async (dispatch) => {
    const updateData = async (loc) => {
      try {
        const response = await axios.put(
          `${API_BASE_URL}/updateAirport`,
          loc,
          {
            withCredentials: true,
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        if (response.status !== 200) {
          throw new Error("Could not update airport data");
        }

        const data = response.data;
        console.log(data);
        return data;
      } catch (error) {
        throw new Error(error);
      }
    };

    try {
      const airportData = await updateData(loc);
      // Dispatch the appropriate action
      console.log(airportData);
    } catch (error) {
      console.error(error);
    }
  };
};
