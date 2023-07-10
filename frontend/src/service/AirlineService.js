import axios from "axios";
import { airlineActions } from "../store/airline-slice";

const API_BASE_URL = "http://localhost:9091/api/airline";
export const getAirlines = () => {
  return async (dispatch) => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`${API_BASE_URL}/getAllAirlines`, {
          withCredentials:true,
          headers: {
            "Content-Type": "application/json",
          },
        });

        if (response.status != 200) {
          throw new Error("Could not fetch airline data");
        }

        const data = response.data;
        console.log(data);
        return data;
      } catch (error) {
        throw new Error(error);
      }
    };

    const airlineData = await fetchData();
    dispatch(airlineActions.replaceAirlines(airlineData));
  };
};

export const addAirline = (airline) => {
  return async (dispatch) => {
    const addData = async (airline) => {
      try {
        console.log(airline);
        const response = await axios.post(
          `${API_BASE_URL}/addAirline`,
          airline,
          {
            withCredentials: true,
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        if (response.status != 200) {
          throw new Error("Could not add airline data");
        }

        const data = response.data;
        console.log(data);
        return data;
      } catch (error) {
        throw new Error(error);
      }
    };

    const airlineData = await addData(airline);
    dispatch(airlineActions.addAirline(airlineData));
  };
};

export const deleteAirline = (loc) => {
  return async (dispatch) => {
    const deleteData = async (loc) => {
      try {
        const response = await axios.delete(`${API_BASE_URL}/deleteAirline`, {
          data: loc,
          withCredentials: true,
          headers: {
            "Content-Type": "application/json",
          },
        });

        if (response.status != 200) {
          throw new Error("Could not delete airline data");
        }

        const data = response.data;
        console.log(data);
        return data;
      } catch (error) {
        throw new Error(error);
      }
    };

    const airlineData = await deleteData(loc);
    if (airlineData === "Successfully Deleted") {
      dispatch(airlineActions.deleteAirline(loc));
    }
  };
};
export const updateAirline = (loc) => {
  return async (dispatch) => {
    const updateData = async (loc) => {
      try {
        const response = await axios.put(
          `${API_BASE_URL}/updateAirline`,
          loc,
          {
            withCredentials: true,
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        if (response.status !== 200) {
          throw new Error("Could not update airline data");
        }

        const data = response.data;
        console.log(data);
        return data;
      } catch (error) {
        throw new Error(error);
      }
    };

    try {
      const airlineData = await updateData(loc);
      // Dispatch the appropriate action
      console.log(airlineData);
    } catch (error) {
      console.error(error);
    }
  };
};
