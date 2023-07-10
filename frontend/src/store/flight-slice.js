import { createSlice } from "@reduxjs/toolkit";

const flightSlice = createSlice({
  name: "flights",
  initialState: {
    flights: [],
  },
  reducers: {
    replaceFlights(state, action) {
        state.flights = action.payload;
      },
    addFlight(state, action) {
      console.log(action.payload);
      state.flights.push(action.payload);
      console.log(state.flights);
    },
    deleteFlight(state, action) {
      const filteredFlights = state.flights.filter(
        (flight) => flight.id !== action.payload.id
      );
      state.flights = filteredFlights;
    },

    updateFlight(state, action) {
      const updatedFlights = state.flights.map((flight) => {
        if (flight.id === action.payload.id) {
          return action.payload;
        }
        return flight;
      });
      state.flights = updatedFlights;
    },
  },
});
export const flightActions = flightSlice.actions;
export default flightSlice;
