import "./App.css";
import Payment from "./component/Payment";
import Schedule from "./component/Schedule";
import Home from "./component/Home";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import NavBar from "./component/NavBar";
import Register from "./component/Register";
import Login from "./component/Login";
import AuthForm from "./component/Sign";
import Logout from "./component/Logout";
import SchedulesResult from "./component/SchedulesResult";
import { validate } from "./service/AuthService";
import { useDispatch } from "react-redux";
import Booking from "./component/Booking";
import Cancel from "./component/Cancel";
import Success from "./component/Success";
import AllBooking from "./component/AllBookings";
import { createMuiTheme } from "@mui/material";



function App() {
  const dispatch = useDispatch();
  dispatch(validate());

  

  return (
    <BrowserRouter>
      <NavBar />
      <Routes>
        <Route path="/" Component={Home}></Route>
        <Route path="/flight" Component={Schedule}></Route>
        <Route path="/sign" Component={AuthForm}></Route>
        <Route path="/register" Component={Register}></Route>
        <Route path="/login" Component={Login}></Route>
        <Route path="/logout" Component={Logout}></Route>
        <Route path="/schedulesResult" Component={SchedulesResult}></Route>
        <Route path="/booking" Component={Booking}></Route>
        <Route path="/allBooking" Component={AllBooking}></Route>

        <Route path="/cancel" Component={Cancel}></Route>
        <Route path="/success" Component={Success}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
