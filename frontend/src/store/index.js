import { configureStore } from "@reduxjs/toolkit";
import locationSlice from "./location-slice";
import scheduleSlice from "./schedules-slice";
import authSlice from "./auth-slice";
import resultSlice from "./result-slice";

const store = configureStore({
  reducer: {
    schedule: scheduleSlice.reducer,
    location: locationSlice.reducer,
    auth:authSlice.reducer,
    schedulesResult: resultSlice.reducer,
  },
});

export default store;
