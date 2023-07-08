import { createSlice } from "@reduxjs/toolkit";

const allBookingsSlice = createSlice({
  name: "allBookings",
  initialState: {
    bookings: [],
  },
  reducers: {
    replaceBookings(state, action) {
      state.bookings = action.payload;
    },
    addToBookings(state, action) {
      state.push(action.payload);
    },

    deleteBooking(state, action) {
      const filteredBookings = state.bookings.filter(
        (booking) => booking.id !== action.payload.id
      );
      state.bookings = filteredBookings;
    },

    updateBooking(state, action) {
      const updatedBookings = state.bookings.map((booking) => {
        if (booking.id === action.payload.id) {
          return { ...booking, checkedIn: true };
        }
      });
      state.bookings = updatedBookings;
    },
  },
});
export const allBookingsActions = allBookingsSlice.actions;
export default allBookingsSlice;
