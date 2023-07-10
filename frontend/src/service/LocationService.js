import axios from "axios";
import { locationActions } from "../store/location-slice";

const API_BASE_URL = "http://localhost:9091/api/location";
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

export const addLocation = (location) => {
  return async (dispatch) => {
    const addData = async (location) => {
      try {
        console.log(location);
        const response = await axios.post(
          `${API_BASE_URL}/addLocation`,
          location,
          {
            withCredentials: true,
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        if (response.status != 200) {
          throw new Error("Could not add location data");
        }

        const data = response.data;
        console.log(data);
        return data;
      } catch (error) {
        throw new Error(error);
      }
    };

    const locationData = await addData(location);
    dispatch(locationActions.addLocation(locationData));
  };
};

export const deleteLocation = (loc) => {
  return async (dispatch) => {
    const deleteData = async (loc) => {
      try {
        const response = await axios.delete(`${API_BASE_URL}/deleteLocation`, {
          data: loc,
          withCredentials: true,
          headers: {
            "Content-Type": "application/json",
          },
        });

        if (response.status != 200) {
          throw new Error("Could not delete location data");
        }

        const data = response.data;
        console.log(data);
        return data;
      } catch (error) {
        throw new Error(error);
      }
    };

    const locationData = await deleteData(loc);
    if (locationData === "Successfully Deleted") {
      dispatch(locationActions.deleteLocation(loc));
    }
  };
};
export const updateLocation = (loc) => {
  return async (dispatch) => {
    const updateData = async (loc) => {
      try {
        const response = await axios.put(
          `${API_BASE_URL}/updateLocation`,
          loc,
          {
            withCredentials: true,
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        if (response.status !== 200) {
          throw new Error("Could not update location data");
        }

        const data = response.data;
        console.log(data);
        return data;
      } catch (error) {
        throw new Error(error);
      }
    };

    try {
      const locationData = await updateData(loc);
      // Dispatch the appropriate action
      console.log(locationData);
    } catch (error) {
      console.error(error);
    }
  };
};
