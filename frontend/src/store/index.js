import { configureStore } from "@reduxjs/toolkit";
import locationSlice from "./location-slice";
import scheduleSlice from "./schedules-slice";
import authSlice from "./auth-slice";
import resultSlice from "./result-slice";
import passengerSlice from "./passenger-slice";

const store = configureStore({
  reducer: {
    schedule: scheduleSlice.reducer,
    location: locationSlice.reducer,
    auth:authSlice.reducer,
    schedulesResult: resultSlice.reducer,
    passenger:passengerSlice.reducer,
  },
});

export default store;
