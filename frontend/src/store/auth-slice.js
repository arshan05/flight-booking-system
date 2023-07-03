import { createSlice } from "@reduxjs/toolkit";

const authSlice = createSlice({
  name: "auth",
  initialState: {
    isAuthenticated: false,
    email:'',
    role:'',
    token:''
  },
  reducers: {
    authenticate(state, action) {
        state.isAuthenticated = action.payload.isAuthenticated;
        state.email = action.payload.email;
        state.role = action.payload.role;
        state.token = action.payload.token;
      },
  },
});
export const authActions = authSlice.actions;
export default authSlice;
