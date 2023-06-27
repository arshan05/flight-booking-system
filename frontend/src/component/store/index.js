import { createSlice } from "@reduxjs/toolkit";

const scheduleSlice = createSlice({
    name: 'schedule',
    initialState: {
        schedules : []
    },
    reducers : {
        addSchedule(state,action){
            // const newSchedule = action.payload;
            // const existingSchedule = state.items.find((item) => item.id === newItem.id)
        }
    }
})