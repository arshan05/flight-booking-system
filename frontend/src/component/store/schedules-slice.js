import { configureStore } from '@reduxjs/toolkit';

const store = configureStore({
    reducer: {schedule: scheduleSlice.reducer}
});

export default store;
