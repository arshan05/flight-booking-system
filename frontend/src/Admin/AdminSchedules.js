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
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DateTimePicker } from "@mui/x-date-pickers";

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
  const [scheduleStatus, setScheduleStatus] = useState("");
  const [baseFare, setBaseFare] = useState("");

  const scheduleStatusOptions = ["DELAY", "ONTIME", "CANCELLED"];

  const handleAddSchedule = (event) => {
    event.preventDefault();
    const scheduleData = {
      flight: flight,
      boarding: boarding,
      destination: destination,
      startTime: startTime,
      endTime: endTime,
      scheduleStatus: scheduleStatus,
      baseFare: baseFare,
    };
    console.log(scheduleData);
    dispatch(addSchedule(scheduleData));
  };
  console.log(useSelector((state) => state.schedule));
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
                  <MenuItem value="">Select a flight</MenuItem>
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

              <FormControl fullWidth margin="normal">
                <FormLabel>Start Time</FormLabel>
                <LocalizationProvider dateAdapter={AdapterDayjs}>
                  <DateTimePicker
                    value={startTime}
                    onChange={(value) => {
                      setStartTime(
                        String(value.format("YYYY-MM-DDTHH:mm:ssZ"))
                      );
                    }}
                    renderInput={(props) => (
                      <TextField {...props} variant="standard" fullWidth />
                    )}
                    minDateTime={dayjs().startOf("minute")}
                  />
                </LocalizationProvider>
              </FormControl>

              <FormControl fullWidth margin="normal">
                <FormLabel>End Time</FormLabel>
                <LocalizationProvider dateAdapter={AdapterDayjs}>
                  <DateTimePicker
                    views={["day", "year", "hours", "minutes", "seconds"]}
                    value={endTime}
                    onChange={(value) => {
                      setEndTime(String(value.format("YYYY-MM-DDTHH:mm:ssZ")));
                    }}
                    renderInput={(props) => (
                      <TextField {...props} variant="standard" fullWidth />
                    )}
                    minDateTime={dayjs(startTime).startOf("minute")}
                  />
                </LocalizationProvider>
              </FormControl>

              <FormControl fullWidth variant="standard" margin="normal">
                <InputLabel id="select-label">Status</InputLabel>

                <Select
                  labelId="select-label"
                  margin="normal"
                  fullWidth
                  label="Status"
                  value={scheduleStatus}
                  onChange={(event) => {
                    setScheduleStatus(event.target.value);
                  }}
                >
                  <MenuItem value="">Select Schedule Status</MenuItem>
                  {scheduleStatusOptions.map((option) => (
                    <MenuItem value={option}>
                      <Box className="d-flex flex-row justify-content around align-items-center">
                        <Typography variant="subtitle1">{option}</Typography>
                      </Box>
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>

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
        {schedules.map((item) => (
          <Grid item sm={6} lg={3} key={item.id}>
            <AdminScheduleItem schedule={item} />
          </Grid>
        ))}
      </Grid>
    </div>
  );
};

export default AdminSchedules;
