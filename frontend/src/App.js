import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { BrowserRouter, Route, Routes } from "react-router-dom";
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
import { ToastContainer } from "react-toastify";

function App() {
  const dispatch = useDispatch();

  const auth = useSelector((state) => state.auth);
  useEffect(
    () => {
      dispatch(validate());
    },
    [dispatch],
    auth
  );

  return (
    <div>
      <BrowserRouter>
        <NavBar />
        <ToastContainer
        position="top-right"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="light"
      />
        <Routes>
          <Route
            path="/"
            element={
              auth.role.includes("admin") ? <AdminHome /> : <Home />
            }
          />
          <Route path="/home" element={<Home />} />
          <Route path="/flight" element={<Schedule />} />
          <Route path="/sign" element={<AuthForm />} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
          <Route path="/logout" element={<Logout />} />
          <Route path="/schedulesResult" element={<SchedulesResult />} />
          <Route path="/booking" element={<Booking />} />
          <Route path="/allBooking" element={<AllBooking />} />
          <Route path="/cancel" element={<Cancel />} />
          <Route path="/success" element={<Success />} />
          <Route path="/boardingpass" element={<BoardingPass />} />

          {/* admin */}
          <Route path="/adminHome" element={<AdminHome />} />
          <Route path="/admin/flights" element={<AdminFlights />} />
          <Route path="/admin/airports" element={<AdminAirports />} />
          <Route path="/admin/airlines" element={<AdminAirlines />} />
          <Route path="/admin/locations" element={<AdminLocations />} />
          <Route path="/admin/schedules" element={<AdminSchedules />} />
        </Routes>
      </BrowserRouter>
      
    </div>
  );
}

export default App;
