import { createSlice } from "@reduxjs/toolkit";

const airportSlice = createSlice({
  name: "airports",
  initialState: {
    airports: [],
  },
  reducers: {
    replaceAirports(state, action) {
        state.airports = action.payload;
      },
    addAirport(state, action) {
      console.log(action.payload);
      state.airports.push(action.payload);
      console.log(state.airports);
    },
    deleteAirport(state, action) {
      const filteredAirports = state.airports.filter(
        (airport) => airport.id !== action.payload.id
      );
      state.airports = filteredAirports;
    },

    updateAirport(state, action) {
      const updatedAirports = state.airports.map((airport) => {
        if (airport.id === action.payload.id) {
          return action.payload;
        }
        return airport;
      });
      state.airports = updatedAirports;
    },
  },
});
export const airportActions = airportSlice.actions;
export default airportSlice;
