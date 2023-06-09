import { useEffect, useState } from "react";
import "../style/style.css";
import { getLocations } from "../service/LocationService";
import { useDispatch, useSelector } from "react-redux";
import { Autocomplete, Button, TextField } from "@mui/material";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import { getFlights } from "../service/ConsumerService";
import { format } from "date-fns";
import { Link, useNavigate } from "react-router-dom";
import dayjs from "dayjs";
import "../style/schedule.css";
import { getAirlines } from "../service/AirlineService";
import { getAirports } from "../service/AirportService";

const myStyle = {
  backgroundColor: "#f5f5f5",
  color: "black",
  padding: "10px",
  fontSize: "16px",
  borderRadius: "5px",
  fontWeight: "bold",
  opacity: 1,
};

const Home = () => {
  // ScheduleService.getAllSchedules();
  const dispatch = useDispatch();
  const locations = useSelector((state) => state.location.locations);
  const schedulesResult = useSelector(
    (state) => state.schedulesResult.schedulesResult
  );
  const today = dayjs();
  const navigate = useNavigate();

  const [fromField, setFromField] = useState("");
  const [toField, setToField] = useState("");
  const [dateField, setDateField] = useState(today);

  const auth = useSelector((state) => state.auth);

  useEffect(() => {
    dispatch(getLocations());
    console.log(locations);
  }, [dispatch]);

  const submitHandler = async (event) => {
    event.preventDefault();
    const flightDetails = {
      start: fromField,
      destination: toField,
      date: dateField,
    };

    // console.log(flightDetails);
    // ConsumerService.getFlights(flightDetails);
    console.log(schedulesResult);
    // dispatch(getFlights(flightDetails));
    navigate("/schedulesResult", { state: flightDetails });
    // console.log(schedulesResult);
  };

  return (
    <div className="d-flex flex-column justify-content-center">
      <div className="image-container m-2">
        <img
          src="https://images.pexels.com/photos/1465904/pexels-photo-1465904.jpeg?auto=compress&cs=tinysrgb&w=1600"
          className="rounded d-block"
        ></img>
      </div>
      <div
        className="m-2 row rounded"
        style={{
          backgroundImage:
            "url(https://images.pexels.com/photos/1465904/pexels-photo-1465904.jpeg?auto=compress&cs=tinysrgb&w=1600)",
          backgroundSize: "cover",
          backgroundRepeat: "no-repeat",
          backgroundPosition: "center",
        }}
      >
        <div className="d-flex justify-content-center align-items-end">
          <div
            className="card m-5 p-3 col-lg-8 col-sm-10 bg-opacity-50"
            style={myStyle}
          >
            <h5 className="card-title">Flight Details</h5>
            <div className="card-body">
              <form onSubmit={submitHandler}>
                <div className="mb-3 d-flex flex-row justify-content-around">
                  <div className="col-3">
                    <Autocomplete
                      id="fromDropdown"
                      options={locations}
                      getOptionLabel={(option) => option.place}
                      onChange={(event, value) => {
                        console.log(value.place);
                        const place = value.place;
                        setFromField(place);
                      }}
                      disableClearable
                      renderInput={(params) => (
                        <TextField
                          {...params}
                          label="From"
                          variant="outlined"
                          required
                        />
                      )}
                    />
                  </div>

                  <div className="col-3">
                    <Autocomplete
                      id="toDropdpwn"
                      options={locations}
                      getOptionLabel={(option) => option.place}
                      onChange={(event, value) => {
                        console.log(value.place);
                        const place = value.place;
                        setToField(place);
                      }}
                      disableClearable
                      renderInput={(params) => (
                        <TextField
                          {...params}
                          label="To"
                          variant="outlined"
                          required
                        />
                      )}
                    />
                  </div>
                  <div className="col-3">
                    <LocalizationProvider
                      className="col-3"
                      dateAdapter={AdapterDayjs}
                    >
                      <DatePicker
                        label="Date"
                        format="DD/MM/YYYY"
                        defaultValue={dateField}
                        onChange={(date) => {
                          console.log(date);
                          setDateField(`${date.$y}-${date.$M}-${date.$D}`);
                        }}
                      />
                    </LocalizationProvider>
                  </div>
                </div>
                <div className="d-flex flex-row justify-content-center">
                  <Button
                    type="submit"
                    className="item"
                    variant="contained"
                    style={{ backgroundColor: "#142c54" }}
                  >
                    search
                    {/* <Link to="/schedulesResult">Search Flights</Link> */}
                  </Button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;
