import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  BrowserRouter,
  Route,
  Routes
} from "react-router-dom";
import AdminAirlines from "./Admin/AdminAirlines";
import AdminAirports from "./Admin/AdminAirports";
import AdminFlights from "./Admin/AdminFlights";
import AdminHome from "./Admin/AdminHome";
import AdminLocations from "./Admin/AdminLocation";
import AdminSchedules from "./Admin/AdminSchedules";
import "./App.css";
import AllBooking from "./component/AllBookings";
import BoardingPass from "./component/BoardingPass";
import Booking from "./component/Booking";
import Cancel from "./component/Cancel";
import Home from "./component/Home";
import Login from "./component/Login";
import Logout from "./component/Logout";
import NavBar from "./component/NavBar";
import Register from "./component/Register";
import Schedule from "./component/Schedule";
import SchedulesResult from "./component/SchedulesResult";
import AuthForm from "./component/Sign";
import Success from "./component/Success";
import { validate } from "./service/AuthService";

function App() {
  const dispatch = useDispatch();
  

  const auth = useSelector((state) => state.auth);
  useEffect(() => {
    dispatch(validate());
  },[dispatch],auth);

  return (
    <BrowserRouter>
      <NavBar />
      <Routes>
        <Route
          path="/"
          Component={auth.role.includes("admin") ? AdminHome : Home}
        />

        <Route path="/home" Component={Home}></Route>
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
        <Route path="/boardingpass" Component={BoardingPass}></Route>

        {/* admin */}
        <Route path="/adminHome" Component={AdminHome}></Route>
        <Route path="/admin/flights" Component={AdminFlights}></Route>
        <Route path="/admin/airports" Component={AdminAirports}></Route>
        <Route path="/admin/airlines" Component={AdminAirlines}></Route>
        <Route path="/admin/locations" Component={AdminLocations}></Route>
        <Route path="/admin/schedules" Component={AdminSchedules}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
