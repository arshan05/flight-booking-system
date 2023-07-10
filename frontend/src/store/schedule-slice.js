import { createSlice } from "@reduxjs/toolkit";

const scheduleSlice = createSlice({
  name: "schedules",
  initialState: {
    schedules: [],
  },
  reducers: {
    replaceSchedules(state, action) {
        state.schedules = action.payload;
      },
    addSchedule(state, action) {
      console.log(action.payload);
      state.schedules.push(action.payload);
      console.log(state.schedules);
    },
    deleteSchedule(state, action) {
      const filteredSchedules = state.schedules.filter(
        (schedule) => schedule.id !== action.payload.id
      );
      state.schedules = filteredSchedules;
    },

    updateSchedule(state, action) {
      const updatedSchedules = state.schedules.map((schedule) => {
        if (schedule.id === action.payload.id) {
          return action.payload;
        }
        return schedule;
      });
      state.schedules = updatedSchedules;
    },
  },
});
export const scheduleActions = scheduleSlice.actions;
export default scheduleSlice;
