import {
  faBuilding,
  faCalendarAlt,
  faUser,
} from "@fortawesome/free-regular-svg-icons";
import {
  faMapMarkerAlt,
  faPlane,
  faPlaneDeparture,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Container, FormLabel, Grid, Paper } from "@mui/material";

import "../style/AdminHome.css";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { getLocations } from "../service/LocationService";
import { getAirlines } from "../service/AirlineService";
import { getAirports } from "../service/AirportService";
import { getFlights } from "../service/FlightService";
import { getSchedules } from "../service/ScheduleService";

const AdminHome = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getLocations())
    dispatch(getAirlines());
    dispatch(getAirports());
    dispatch(getFlights());
    dispatch(getSchedules())
  }, [dispatch]);

  const cardData = [
    { title: "Flights", icon: faPlane },
    { title: "Airports", icon: faBuilding },
    { title: "Airlines", icon: faPlaneDeparture },
    { title: "Schedules", icon: faCalendarAlt },
    { title: "Locations", icon: faMapMarkerAlt },
    { title: "Passengers", icon: faUser },
  ];

  const handleClick = (title) => {
    if (title === "Flights") {
      navigate("/admin/flights");
    } else if (title === "Airports") {
      navigate("/admin/airports");
    } else if (title === "Passengers") {
      navigate("/admin/passengers");
    } else if (title === "Schedules") {
      navigate("/admin/schedules");
    } else if (title === "Locations") {
      navigate("/admin/locations");
    } else if (title === "Airlines") {
      navigate("/admin/airlines");
    }
  };

  return (
    <Container maxWidth="lg" className="admin-dashboard">
      <div className="fs-1 my-3" style={{ color: "#142c54" }}>
        Admin Dashboard
      </div>

      <Grid container spacing={3}>
        {cardData.map((card, index) => (
          <Grid item xs={4} key={index}>
            <Paper
              className="dashboard-card d-flex flex-column justify-content-center align-items-center text-center"
              style={{ backgroundColor: "lavender" }}
              onClick={() => {
                handleClick(card.title);
              }}
            >
              <div className="icon-wrapper">
                <FontAwesomeIcon icon={card.icon} className="card-icon" />
                <div>
                  <FormLabel>{card.title}</FormLabel>
                </div>
              </div>
              <div className="card-overlay">
                <h3>{card.title}</h3>
              </div>
            </Paper>
          </Grid>
        ))}
      </Grid>
    </Container>
  );
};

export default AdminHome;
