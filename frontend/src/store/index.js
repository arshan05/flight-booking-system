import { configureStore } from "@reduxjs/toolkit";
import locationSlice from "./location-slice";
import scheduleSlice from "./schedules-slice";

const store = configureStore({
  reducer: { schedule: scheduleSlice.reducer, location: locationSlice.reducer },
});

export default store;
