import { createSlice } from "@reduxjs/toolkit";
import LocationService from "../service/LocationService";

const passengerSlice = createSlice({
  name: "passenger",
  initialState: {
    id: "",
    name: "",
    email: "",
    phoneNumber: "",
    bookingDetails:"",
  },
  reducers: {
    setPassenger(state, action) {
        state.id = action.payload.id;
        state.name = action.payload.name;
        state.email = action.payload.email;
        state.phoneNumber = action.payload.phoneNumber;
        state.bookingDetails = action.payload.bookingDetails;
      },
      
    setName(state,action){
        state.name = action.payload.name;
    },
    setPhoneNumber(state,action){
        state.phoneNumber = action.payload.phoneNumber;
    },
    setId(state,action){
        state.id = action.payload.id;
    }
  },
});
export const passengerActions = passengerSlice.actions;
export default passengerSlice;
