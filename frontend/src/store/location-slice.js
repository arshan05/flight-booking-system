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
      console.log(action.payload);
      state.locations.push(action.payload);
      console.log(state.locations);
    },
    deleteLocation(state, action) {
      const filteredLocations = state.locations.filter(
        (location) => location.id !== action.payload.id
      );
      state.locations = filteredLocations;
    },

    updateLocation(state, action) {
      const updatedLocations = state.locations.map((loc) => {
        if (loc.id === action.payload.id) {
          return action.payload;
        }
        return loc;
      });
      state.locations = updatedLocations;
    },
  },
});
export const locationActions = locationSlice.actions;
export default locationSlice;
