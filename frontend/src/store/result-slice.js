import { createSlice } from "@reduxjs/toolkit";

const resultSlice = createSlice({
  name: "schedulesResult",
  initialState: {
    schedulesResult:[]
  },
  reducers: {
    replaceScheduleResult(state, action) {
        state.schedulesResult = action.payload;
      },
  },
});
export const resultActions = resultSlice.actions;
export default resultSlice;
