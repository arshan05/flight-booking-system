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
import { deleteFlight, updateFlight } from "../service/FlightService";
import { updateLocation } from "../service/LocationService";

function AdminFlightItem(props) {
  const dispatch = useDispatch();
  const [editMode, setEditMode] = useState(false);
  const [openDeleteDialog, setOpenDeleteDialog] = useState(false);

  const flightNumber = props.flight.flightNumber;
  const airline = props.flight.airlineCompany;
  const seatCapacity = props.flight.seatCapacity;
  const numberOfColumns = props.flight.numberOfColumns;

  const [updatedFlightNumber, setUpdatedFlightNumber] = useState(flightNumber);
  const [updatedAirline, setUpdatedAirline] = useState(airline);
  const [updatedSeatCapacity, setUpdatedSeatCapacity] = useState(seatCapacity);
  const [updatedNumberOfColumns, setUpdatedNumberOfColumns] =
    useState(numberOfColumns);

  const airlines = useSelector((state) => state.airline.airlines);

  const handleDelete = () => {
    setOpenDeleteDialog(false);
    dispatch(deleteFlight(props.flight));
  };

  const handleUpdate = () => {
    const updatedFlight = {
      id: props.flight.id,
      flightNumber: updatedFlightNumber,
      airlineCompany: updatedAirline,
      seatCapacity: updatedSeatCapacity,
      numberOfColumns: updatedNumberOfColumns,
      seat: props.flight.seat,
      schedule: props.flight.schedule,
    };
    dispatch(updateFlight(updatedFlight));
    setEditMode(false);
  };

  console.log(airline);
  console.log(updatedAirline);
  return (
    <div>
      <Card className="m-3">
        <CardContent className="card-body">
          {editMode && (
            <div className="m-3">
              <div className="my-1">
                <FormLabel>Flight Number:</FormLabel>
                <TextField
                  margin="none"
                  fullWidth
                  id="flightName"
                  type="text"
                  value={updatedFlightNumber}
                  required
                  variant="standard"
                  onChange={(event) => {
                    setUpdatedFlightNumber(event.target.value);
                  }}
                />
              </div>

              <FormControl fullWidth variant="standard" margin="normal">
                <InputLabel id="select-label">Airline Company</InputLabel>

                <Select
                  labelId="select-label"
                  margin="normal"
                  fullWidth
                  label="Airline"
                  value={updatedAirline}
                  onChange={(event) => {
                    setUpdatedAirline(event.target.value);
                  }}
                >
                  <MenuItem value="">Select a airline</MenuItem>
                  {airlines.map((air) => (
                    <MenuItem key={air.id} value={air}>
                      <Box className="d-flex flex-row justify-content around align-items-center">
                        <Typography variant="subtitle1">
                          {air.airlineName} ({air.code})
                        </Typography>
                      </Box>
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
            </div>
          )}

          {!editMode && (
            <div className="m-3">
              <FormLabel>Flight Number:</FormLabel>
              <p className="text fs-6">{updatedFlightNumber}</p>

              <FormLabel>Airline:</FormLabel>
              <Box margin="normal" className="d-flex flex-column">
                <Typography variant="subtitle1">
                  {updatedAirline.airlineName}
                </Typography>
                <Typography variant="body2" color="textSecondary">
                  {updatedAirline.code}
                </Typography>
              </Box>

              <FormLabel>Seat Capacity:</FormLabel>
              <p className="text fs-6">{updatedSeatCapacity}</p>

              <FormLabel>Number of Columns:</FormLabel>
              <p className="text fs-6">{updatedNumberOfColumns}</p>
            </div>
          )}
        </CardContent>

        <CardActions className="card-footer">
          {!editMode && (
            <div className="d-flex justify-content-end">
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
      </Card>
    </div>
  );
}

export default AdminFlightItem;
