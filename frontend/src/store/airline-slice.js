import { createSlice } from "@reduxjs/toolkit";

const airlineSlice = createSlice({
  name: "airlines",
  initialState: {
    airlines: [],
  },
  reducers: {
    replaceAirlines(state, action) {
        state.airlines = action.payload;
      },
    addAirline(state, action) {
      console.log(action.payload);
      state.airlines.push(action.payload);
      console.log(state.airlines);
    },
    deleteAirline(state, action) {
      const filteredAirlines = state.airlines.filter(
        (airline) => airline.id !== action.payload.id
      );
      state.airlines = filteredAirlines;
    },

    updateAirline(state, action) {
      const updatedAirlines = state.airlines.map((airline) => {
        if (airline.id === action.payload.id) {
          return action.payload;
        }
        return airline;
      });
      state.airlines = updatedAirlines;
    },
  },
});
export const airlineActions = airlineSlice.actions;
export default airlineSlice;
