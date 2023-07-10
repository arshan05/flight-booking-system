import { useDispatch, useSelector } from "react-redux";
import AdminScheduleItem from "./AdminScheduleItem";
import {
  Box,
  Button,
  Card,
  CardActions,
  CardContent,
  CardHeader,
  Collapse,
  FormControl,
  FormLabel,
  Grid,
  IconButton,
  InputLabel,
  MenuItem,
  Select,
  TextField,
  Typography,
} from "@mui/material";
import { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faChevronDown, faChevronUp } from "@fortawesome/free-solid-svg-icons";
import { addSchedule } from "../service/ScheduleService";
import { Form } from "react-bootstrap";
import dayjs from "dayjs";
import {
  DatePicker,
  DateTimePicker,
  LocalizationProvider,
} from "@mui/x-date-pickers";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";

const AdminSchedules = () => {
  // scheduleNumber
  // airlineCompany
  // seatCapacity
  // numberOfColumns
  // seat
  // schedules
  const dispatch = useDispatch();
  const schedules = useSelector((state) => state.schedule.schedules);
  const airports = useSelector((state) => state.airport.airports);
  const [expanded, setExpanded] = useState(false);

  const flights = useSelector((state) => state.flight.flights);

  const [flight, setFlight] = useState("");
  const [boarding, setBoarding] = useState("");
  const [destination, setDestination] = useState("");
  const [startTime, setStartTime] = useState("");
  const [endTime, setEndTime] = useState("");
  const [status, setStatus] = useState("");
  const [baseFare, setBaseFare] = useState("");

  const handleAddSchedule = (event) => {
    event.preventDefault();
    const scheduleData = {
      flight: flight,
      boarding: boarding,
      destination: destination,
      startTime: startTime,
      endTime: endTime,
      status,
      status,
      baseFare: baseFare,
    };
    console.log(scheduleData);
    // dispatch(addSchedule(scheduleData));
  };

  return (
    <div>
      <Card className="m-3">
        <CardHeader
          title="Add Schedule"
          style={{ color: "#142c54" }}
          action={
            <IconButton
              aria-expanded={expanded}
              aria-label="show more"
              onClick={() => {
                setExpanded(!expanded);
              }}
            >
              {!expanded && <FontAwesomeIcon icon={faChevronDown} />}
              {expanded && <FontAwesomeIcon icon={faChevronUp} />}
            </IconButton>
          }
          onClick={() => {
            setExpanded(!expanded);
          }}
        />
        <Collapse in={expanded} timeout="auto" unmountOnExit>
          <Form onSubmit={handleAddSchedule}>
            <CardContent>
              <FormControl fullWidth variant="standard" margin="normal">
                <InputLabel id="select-label">Flight</InputLabel>

                <Select
                  labelId="select-label"
                  margin="normal"
                  fullWidth
                  label="Flight"
                  value={flight}
                  onChange={(event) => {
                    setFlight(event.target.value);
                  }}
                >
                  <MenuItem value="">Select a airline</MenuItem>
                  {flights.map((fly) => (
                    <MenuItem key={fly.id} value={fly}>
                      <Box className="d-flex flex-row justify-content around align-items-center">
                        <Typography variant="subtitle1">
                          {fly.flightNumber} ({fly.airlineCompany.airlineName})
                        </Typography>
                      </Box>
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>

              <FormControl fullWidth variant="standard" margin="normal">
                <InputLabel id="select-label">Boarding Airport</InputLabel>

                <Select
                  labelId="select-label"
                  margin="normal"
                  fullWidth
                  label="Board Airport"
                  value={boarding}
                  onChange={(event) => {
                    setBoarding(event.target.value);
                  }}
                >
                  <MenuItem value="">Select a boarding airport</MenuItem>
                  {airports.map((air) => (
                    <MenuItem key={air.id} value={air}>
                      <Box className="d-flex flex-row justify-content around align-items-center">
                        <Typography variant="subtitle1">
                          {air.airportName} ({air.code})
                        </Typography>
                      </Box>
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>

              <FormControl fullWidth variant="standard" margin="normal">
                <InputLabel id="select-label">Destination Airport</InputLabel>

                <Select
                  labelId="select-label"
                  margin="normal"
                  fullWidth
                  label="Destination Airport"
                  value={destination}
                  onChange={(event) => {
                    setDestination(event.target.value);
                  }}
                >
                  <MenuItem value="">Select a destinatio Airport</MenuItem>
                  {airports.map((air) => (
                    <MenuItem key={air.id} value={air}>
                      <Box className="d-flex flex-row justify-content around align-items-center">
                        <Typography variant="subtitle1">
                          {air.airportName} ({air.code})
                        </Typography>
                      </Box>
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>

              <div>
                <LocalizationProvider
                  className="col-3"
                  dateAdapter={AdapterDayjs}
                >
                  <DateTimePicker
                    value={dayjs("2022-04-17T15:30")}
                    onChange={(value) => {
                      setStartTime(value.format("YYYY-MM-DDTHH:mm:ssZ"));
                    }}
                  />
                  <DateTimePicker
                    value={dayjs("2022-04-17T15:30")}
                    onChange={(value) => {
                      setEndTime(value.format("YYYY-MM-DDTHH:mm:ssZ"));
                    }}
                  />
                </LocalizationProvider>
              </div>

              <div>
                <LocalizationProvider
                  className="col-3"
                  dateAdapter={AdapterDayjs}
                >
                  <DateTimePicker
                    value={dayjs("2022-04-17T15:30")}
                    onChange={(value) => {
                      setStartTime(value.format("YYYY-MM-DDTHH:mm:ssZ"));
                    }}
                  />
                  <DateTimePicker
                    value={dayjs("2022-04-17T15:30")}
                    onChange={(value) => {
                      setEndTime(value.format("YYYY-MM-DDTHH:mm:ssZ"));
                    }}
                  />
                </LocalizationProvider>
              </div>
              
              <TextField
                label="Status"
                margin="normal"
                fullWidth
                id="status"
                type="text"
                required
                variant="standard"
                onChange={(event) => {
                  setStatus(event.target.value);
                }}
              />

              <TextField
                label="Base fare"
                margin="normal"
                fullWidth
                id="state"
                type="text"
                required
                variant="standard"
                onChange={(event) => {
                  setBaseFare(event.target.value);
                }}
              />
            </CardContent>
            <CardActions className="d-flex justify-content-center">
              <Button fullWidth variant="contained" type="submit">
                Add
              </Button>
            </CardActions>
          </Form>
        </Collapse>
      </Card>

      <Grid container spacing={4}>
        {schedules.map((air) => (
          <Grid item sm={6} lg={3} key={air.id}>
            <AdminScheduleItem schedule={air} />
          </Grid>
        ))}
      </Grid>
    </div>
  );
};

export default AdminSchedules;
