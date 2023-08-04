import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import 'react-toastify/dist/ReactToastify.css';
import reportWebVitals from "./reportWebVitals";
import { Provider } from "react-redux";
import { ThemeProvider, createMuiTheme } from "@mui/material";
import { PersistGate } from "redux-persist/integration/react";
import { persistor, store } from "./store";

<style>
  @import
  url('https://fonts.googleapis.com/css2?family=Belanosima&display=swap');
</style>;

const theme = createMuiTheme({
  typography: {
    fontFamily: "Belanosima",
  },
  palette: {
    primary: {
      main: "#142c54", // Replace with your desired accent color
    },
  },
});

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <Provider store={store}>
    <PersistGate loading={null} persistor={persistor}>
      <ThemeProvider theme={theme}>
        <App />
      </ThemeProvider>
    </PersistGate>
  </Provider>
);
// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
