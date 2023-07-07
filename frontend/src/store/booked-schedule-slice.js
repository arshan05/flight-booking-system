import { createSlice } from "@reduxjs/toolkit";

const bookedScheduleSlice = createSlice({
  name: "bookedSchedule",
  initialState: {
    bookedSchedule: {},
    seatNumber: "",
    passenger:{}
  },
  reducers: {
    setDetails(state, action) {
        state.bookedSchedule = action.payload.bookedSchedule;
        state.seatNumber = action.payload.seatNumber;
        state.passenger = action.payload.passenger;

      },

    setBookedSchedule(state, action) {
      state.bookedSchedule = action.payload.bookedSchedule;
    },
    setSeatNumber(state, action) {
      state.seatNumber = action.payload.seatNumber;
    },
  },
});
export const bookedScheduleSliceActions = bookedScheduleSlice.actions;
export default bookedScheduleSlice;
