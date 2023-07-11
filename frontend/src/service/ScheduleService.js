import axios from "axios";
import { scheduleActions } from "../store/schedule-slice";

const API_BASE_URL = "http://localhost:9091/api/schedule";
export const getSchedules = () => {
  console.log("called");
  return async (dispatch) => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`${API_BASE_URL}/getAllSchedules`, {
          withCredentials:true,
          headers: {
            "Content-Type": "application/json",
          },
        });

        if (response.status != 200) {
          throw new Error("Could not fetch schedule data");
        }

        const data = response.data;
        return data;
      } catch (error) {
        throw new Error(error);
      }
    };

    const scheduleData = await fetchData();
    console.log(scheduleData);
    dispatch(scheduleActions.replaceSchedules(scheduleData));
  };
};

export const addSchedule = (schedule) => {
  return async (dispatch) => {
    const addData = async (schedule) => {
      try {
        console.log(schedule);
        const response = await axios.post(
          `${API_BASE_URL}/addSchedule`,
          schedule,
          {
            withCredentials: true,
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        if (response.status != 200) {
          throw new Error("Could not add schedule data");
        }

        const data = response.data;
        console.log(data);
        return data;
      } catch (error) {
        throw new Error(error);
      }
    };

    const scheduleData = await addData(schedule);
    dispatch(scheduleActions.addSchedule(scheduleData));
  };
};

export const deleteSchedule = (loc) => {
  return async (dispatch) => {
    const deleteData = async (loc) => {
      try {
        const response = await axios.delete(`${API_BASE_URL}/deleteSchedule`, {
          data: loc,
          withCredentials: true,
          headers: {
            "Content-Type": "application/json",
          },
        });

        if (response.status != 200) {
          throw new Error("Could not delete schedule data");
        }

        const data = response.data;
        console.log(data);
        return data;
      } catch (error) {
        throw new Error(error);
      }
    };

    const scheduleData = await deleteData(loc);
    if (scheduleData === "Successfully Deleted") {
      dispatch(scheduleActions.deleteSchedule(loc));
    }
  };
};
export const updateSchedule = (loc) => {
  return async (dispatch) => {
    const updateData = async (loc) => {
      try {
        const response = await axios.put(
          `${API_BASE_URL}/updateSchedule`,
          loc,
          {
            withCredentials: true,
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        if (response.status !== 200) {
          throw new Error("Could not update schedule data");
        }

        const data = response.data;
        console.log(data);
        return data;
      } catch (error) {
        throw new Error(error);
      }
    };

    try {
      const scheduleData = await updateData(loc);
      // Dispatch the appropriate action
      console.log(scheduleData);
    } catch (error) {
      console.error(error);
    }
  };
};
