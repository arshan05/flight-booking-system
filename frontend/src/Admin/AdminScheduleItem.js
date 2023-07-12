import React, { useState } from "react";
import {
  Container,
  TextField,
  Button,
  IconButton,
  FormLabel,
  Card,
  CardContent,
  CardActions,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogContentText,
  DialogActions,
  Box,
  Typography,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
} from "@mui/material";
import {
  faBackward,
  faChevronLeft,
  faPen,
  faPenSquare,
  faTrash,
  faTrashCan,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useDispatch, useSelector } from "react-redux";
import { deleteSchedule, updateSchedule } from "../service/ScheduleService";
import { updateLocation } from "../service/LocationService";
import dayjs from "dayjs";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DateTimePicker } from "@mui/x-date-pickers";

function AdminScheduleItem(props) {
  const dispatch = useDispatch();
  const [editMode, setEditMode] = useState(false);
  const [openDeleteDialog, setOpenDeleteDialog] = useState(false);

  const flight = props.schedule.flight;
  const boarding = props.schedule.boarding;
  const destination = props.schedule.destination;
  const startTime = props.schedule.startTime;
  const endTime = props.schedule.endTime;
  const status = props.schedule.scheduleStatus;
  const baseFare = props.schedule.baseFare;

  const [updatedFlight, setUpdatedFlight] = useState(flight);
  const [updatedBoarding, setUpdatedBoarding] = useState(boarding);
  const [updatedDestination, setUpdatedDestination] = useState(destination);
  const [updatedStartTime, setUpdatedStartTime] = useState(dayjs(startTime));
  const [updatedEndTime, setUpdatedEndTime] = useState(dayjs(endTime));
  const [updatedStatus, setUpdatedStatus] = useState(status);
  const [updatedBaseFare, setUpdatedBaseFare] = useState(baseFare);

  const airports = useSelector((state) => state.airport.airports);
  const flights = useSelector((state) => state.flight.flights);

  const scheduleStatusOptions = ["DELAY", "ONTIME", "CANCELLED"];

  const handleDelete = () => {
    setOpenDeleteDialog(false);
    dispatch(deleteSchedule(props.schedule));
  };

  const handleUpdate = () => {
    const updatedSchedule = {
      id: props.schedule.id,
      flight: updatedFlight,
      boarding: updatedBoarding,
      destination: updatedDestination,
      startTime: updatedStartTime,
      endTime: updatedEndTime,
      scheduleStatus: updatedStatus,
      baseFare: updatedBaseFare,
    };
    console.log(updatedSchedule);
    dispatch(updateSchedule(updatedSchedule));
    setEditMode(false);
  };

  return (
    <div className="scrollable-card">
      <Card className="m-3" style={{height:'550px', overflowY:'auto'}}>
        <CardActions className="card-header card-header d-flex justify-content-end">
          {!editMode && (
            <div className="d-flex justify-content-start">
              <IconButton
                className="shadow-sm m-2"
                onClick={() => {
                  setEditMode(true);
                }}
              >
                <FontAwesomeIcon icon={faPen} color="#142c54" />
              </IconButton>
            </div>
          )}

          {editMode && (
            <div className="d-flex justify-content-between">
              <Button onClick={handleUpdate}>Update</Button>
              <div>
                <IconButton
                  onClick={() => setOpenDeleteDialog(true)}
                  className="shadow-sm m-"
                >
                  <FontAwesomeIcon icon={faTrashCan} color="crimson" />
                </IconButton>

                <Dialog
                  open={openDeleteDialog}
                  onClose={() => {
                    setOpenDeleteDialog(false);
                  }}
                  aria-describedby="alert-dialog-slide-description"
                >
                  <DialogTitle>{"Confirm Delete"}</DialogTitle>
                  <DialogContent>
                    <DialogContentText id="alert-dialog-slide-description">
                      Are you sure you want to delete?
                    </DialogContentText>
                  </DialogContent>
                  <DialogActions>
                    <Button
                      onClick={() => {
                        setOpenDeleteDialog(false);
                      }}
                    >
                      Cancel
                    </Button>
                    <Button onClick={handleDelete}>Delete</Button>
                  </DialogActions>
                </Dialog>

                <IconButton
                  className="shadow-sm m-2"
                  onClick={() => {
                    setEditMode(false);
                  }}
                >
                  <FontAwesomeIcon icon={faChevronLeft} color="#142c54" />
                </IconButton>
              </div>
            </div>
          )}
        </CardActions>
        <CardContent className="card-body">
          {editMode && (
            <div className="m-3">
              <FormControl fullWidth variant="standard" margin="normal">
                <InputLabel id="select-label">Flight: </InputLabel>

                <Select
                  labelId="select-label"
                  margin="normal"
                  fullWidth
                  label="Flight"
                  value={updatedFlight.id}
                  onChange={(event) => {
                    const selectedFlight = flights.find(
                      (fly) => fly.id === event.target.value
                    );
                    setUpdatedFlight(selectedFlight);
                  }}
                >
                  <MenuItem value="">Select an airline</MenuItem>
                  {flights.map((fly) => (
                    <MenuItem key={fly.id} value={fly.id}>
                      {" "}
                      {/* Use the id as the value */}
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
                <InputLabel id="select-label">Boarding: </InputLabel>

                <Select
                  labelId="select-label"
                  margin="normal"
                  fullWidth
                  label="Flight"
                  value={updatedBoarding.id}
                  onChange={(event) => {
                    const selectedBoarding = airports.find(
                      (air) => air.id === event.target.value
                    );
                    setUpdatedBoarding(selectedBoarding);
                  }}
                >
                  <MenuItem value="">Select an airline</MenuItem>
                  {airports.map((air) => (
                    <MenuItem key={air.id} value={air.id}>
                      {" "}
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
                <InputLabel id="select-label">Destination: </InputLabel>

                <Select
                  labelId="select-label"
                  margin="normal"
                  fullWidth
                  label="Destination"
                  value={updatedDestination.id}
                  onChange={(event) => {
                    const selectedDestination = airports.find(
                      (air) => air.id === event.target.value
                    );
                    setUpdatedBoarding(selectedDestination);
                  }}
                >
                  <MenuItem value="">Select an airline</MenuItem>
                  {airports.map((air) => (
                    <MenuItem key={air.id} value={air.id}>
                      {" "}
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
                    value={updatedStartTime}
                    onChange={(value) => {
                      setUpdatedStartTime(
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
                    value={updatedEndTime}
                    onChange={(value) => {
                      setUpdatedEndTime(
                        String(value.format("YYYY-MM-DDTHH:mm:ssZ"))
                      );
                    }}
                    renderInput={(props) => (
                      <TextField {...props} variant="standard" fullWidth />
                    )}
                    minDateTime={dayjs(startTime).startOf("minute")}
                  />
                </LocalizationProvider>
              </FormControl>

              <FormControl fullWidth variant="standard" margin="normal">
                <InputLabel id="select-label">Status: </InputLabel>

                <Select
                  labelId="select-label"
                  margin="normal"
                  fullWidth
                  label="Status"
                  value={updatedStatus}
                  onChange={(event) => {
                    setUpdatedStatus(event.target.value);
                  }}
                >
                  <MenuItem value="">Select an airline</MenuItem>
                  {scheduleStatusOptions.map((stat) => (
                    <MenuItem key={stat} value={stat}>
                      {" "}
                      <Box className="d-flex flex-row justify-content around align-items-center">
                        <Typography variant="subtitle1">{stat}</Typography>
                      </Box>
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>

              <TextField
                label="Base fare"
                margin="normal"
                fullWidth
                type="text"
                required
                variant="standard"
                value={updatedBaseFare}
                onChange={(event) => {
                  setUpdatedBaseFare(event.target.value);
                }}
              />
            </div>
          )}

          {!editMode && (
            <div className="m-3">
              <div className="my-2">
                <FormLabel>Flight:</FormLabel>
                <Box margin="normal" className="d-flex flex-column">
                  <Typography variant="subtitle1">
                    {updatedFlight.flightNumber}
                  </Typography>
                  <Typography variant="body2" color="textSecondary">
                    {updatedFlight.airlineCompany.airlineName}
                  </Typography>
                </Box>
              </div>

              <div className="my-2">
                <FormLabel>Boarding:</FormLabel>
                <Box margin="normal" className="d-flex flex-column">
                  <Typography variant="subtitle1">
                    {updatedBoarding.airportName} ({updatedBoarding.code})
                  </Typography>
                  <Typography variant="body2" color="textSecondary">
                    {updatedBoarding.location.place},{" "}
                    {updatedBoarding.location.country}
                  </Typography>
                </Box>
              </div>

              <div className="my-2">
                <FormLabel>Destination:</FormLabel>
                <Box margin="normal" className="d-flex flex-column">
                  <Typography variant="subtitle1">
                    {updatedDestination.airportName} ({updatedDestination.code})
                  </Typography>
                  <Typography variant="body2" color="textSecondary">
                    {updatedDestination.location.place},{" "}
                    {updatedDestination.location.country}
                  </Typography>
                </Box>
              </div>

              <FormLabel>Base Fare:</FormLabel>
              <p className="text fs-6">{updatedBaseFare}</p>

              <FormLabel>Status:</FormLabel>
              <p className="text fs-6">{updatedStatus}</p>
            </div>
          )}
        </CardContent>
      </Card>
    </div>
  );
}

export default AdminScheduleItem;
