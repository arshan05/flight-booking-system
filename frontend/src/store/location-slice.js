import { createSlice } from "@reduxjs/toolkit";
import LocationService from "../service/LocationService";

const locationSlice = createSlice({
  name: "locations",
  initialState: {
    locations: [],
  },
  reducers: {
    replaceLocations(state, action) {
        state.locations = action.payload;
      },
    addLocation(state, action) {
      state.push(action.payload);
    },
  },
});
export const locationActions = locationSlice.actions;
export default locationSlice;
