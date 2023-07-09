import "./App.css";
import Payment from "./component/Payment";
import Schedule from "./component/Schedule";
import Home from "./component/Home";
import {
  BrowserRouter,
  Route,
  Routes,
  Redirect,
  useNavigate,
} from "react-router-dom";
import NavBar from "./component/NavBar";
import Register from "./component/Register";
import Login from "./component/Login";
import AuthForm from "./component/Sign";
import Logout from "./component/Logout";
import SchedulesResult from "./component/SchedulesResult";
import { validate } from "./service/AuthService";
import { useDispatch, useSelector } from "react-redux";
import Booking from "./component/Booking";
import Cancel from "./component/Cancel";
import Success from "./component/Success";
import AllBooking from "./component/AllBookings";
import { createMuiTheme } from "@mui/material";
import BoardingPass from "./component/BoardingPass";
import AdminHome from "./Admin/AdminHome";
import AdminFlights from "./Admin/AdminFlights";
import AdminAirports from "./Admin/AdminAirports";
import AdminSchedules from "./Admin/AdminSchedules";
import AdminLocations from "./Admin/AdminLocation";
import AdminPassengers from "./Admin/AdminPassenger";
import AdminAirlines from "./Admin/AdminAirlines";

function App() {
  const dispatch = useDispatch();
  dispatch(validate());

  const auth = useSelector((state) => state.auth);

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
        <Route path="/admin/passengers" Component={AdminPassengers}></Route>
        <Route path="/admin/locations" Component={AdminLocations}></Route>
        <Route path="/admin/schedules" Component={AdminSchedules}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
