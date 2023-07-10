import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { persistStore, persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage'; 

import locationSlice from "./location-slice";
import scheduleSlice from "./schedules-slice";
import authSlice from "./auth-slice";
import resultSlice from "./result-slice";
import passengerSlice from "./passenger-slice";
import bookedScheduleSlice from "./booked-schedule-slice";
import sessionStorage from "redux-persist/es/storage/session";
import allBookingsSlice from "./all-bookings-slice";
import airlineSlice from "./airline-slice";
import airportSlice, { airportActions } from "./airport-slice";
import flightSlice from "./flight-slice";

const persistConfig = {
  key: 'root', // Key for the root of your state object
  storage:sessionStorage, // Storage method (localStorage or sessionStorage)
};

// Create a persisted reducer
const persistedReducer = persistReducer(
  persistConfig,
  combineReducers({
    schedule: scheduleSlice.reducer,
    location: locationSlice.reducer,
    auth: authSlice.reducer,
    schedulesResult: resultSlice.reducer,
    passenger: passengerSlice.reducer,
    bookedSchedule: bookedScheduleSlice.reducer,
    allBookings:allBookingsSlice.reducer,
    airline:airlineSlice.reducer,
    airport:airportSlice.reducer,
    flight:flightSlice.reducer,
    schedule:scheduleSlice.reducer,
  })
);

// Create the Redux store
export const store = configureStore({
  reducer: persistedReducer,
});

// Create the persisted store
export const persistor = persistStore(store);



// import { configureStore } from "@reduxjs/toolkit";
// import locationSlice from "./location-slice";
// import scheduleSlice from "./schedules-slice";
// import authSlice from "./auth-slice";
// import resultSlice from "./result-slice";
// import passengerSlice from "./passenger-slice";
// import bookedScheduleSlice from "./booked-schedule-slice";

// const store = configureStore({
//   reducer: {
//     schedule: scheduleSlice.reducer,
//     location: locationSlice.reducer,
//     auth: authSlice.reducer,
//     schedulesResult: resultSlice.reducer,
//     passenger: passengerSlice.reducer,
//     bookedSchedule: bookedScheduleSlice.reducer,
//   },
// });

// export default store;
