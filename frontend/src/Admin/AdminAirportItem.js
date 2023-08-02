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
import { deleteAirport, updateAirport } from "../service/AirportService";
import { updateLocation } from "../service/LocationService";

function AdminAirportItem(props) {
  const dispatch = useDispatch();
  const [editMode, setEditMode] = useState(false);
  const [openDeleteDialog, setOpenDeleteDialog] = useState(false);

  const airportName = props.airport.airportName;
  const code = props.airport.code;
  const location = props.airport.location;

  const [updatedAirportName, setUpdatedAirportName] = useState(airportName);
  const [updatedCode, setUpdatedCode] = useState(code);
  const [updatedLocation, setUpdatedLocation] = useState(location);

  const locations = useSelector((state)=>state.location.locations)

  const handleDelete = () => {
    setOpenDeleteDialog(false);
    dispatch(deleteAirport(props.airport));
  };

  const handleUpdate = () => {
    const updatedAirport = {
      id: props.airport.id,
      airportName: updatedAirportName,
      code: updatedCode,
      location: updatedLocation,
    };
    dispatch(updateAirport(updatedAirport));
    setEditMode(false);
  };

  return (
    <div>
      <Card className="m-3" style={{backgroundColor:'#FFC285', color:'#FF420C', height:"360px"}}>
        
        
        <CardActions className="card-header d-flex justify-content-end">
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
              <div className="my-1">
                <FormLabel>Airport Name:</FormLabel>
                <TextField
                  margin="none"
                  fullWidth
                  id="airportName"
                  type="text"
                  value={updatedAirportName}
                  required
                  variant="standard"
                  onChange={(event) => {
                    setUpdatedAirportName(event.target.value);
                  }}
                />
              </div>

              <div className="my-1">
                <FormLabel>Code:</FormLabel>
                <TextField
                  margin="none"
                  fullWidth
                  value={updatedCode}
                  id="state"
                  type="text"
                  required
                  variant="standard"
                  onChange={(event) => {
                    setUpdatedCode(event.target.value);
                  }}
                />
              </div>
              <FormControl fullWidth variant="standard" margin="normal">
                <InputLabel id="select-label">Location</InputLabel>

                <Select
                  labelId="select-label"
                  margin="normal"
                  fullWidth
                  label="Location"
                  value={updatedLocation}
                  onChange={(event) => {
                    setUpdatedLocation(event.target.value);
                  }}
                >
                  <MenuItem value="">Select a location</MenuItem>
                  {locations.map((loc) => (
                    <MenuItem key={loc.id} value={loc}>
                      <Box className="d-flex flex-column">
                        <Typography variant="subtitle1">{loc.place}</Typography>
                        <Typography variant="body2" color="textSecondary">
                           {loc.state}, {loc.country}
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
              <FormLabel>Airport Name:</FormLabel>
              <p className="text fs-6">{updatedAirportName}</p>

              <FormLabel>Code:</FormLabel>
              <p className="text fs-6">{updatedCode}</p>
            
              <FormLabel>Location:</FormLabel>
              <Box className="d-flex flex-column">
              <Typography variant="subtitle1">{updatedLocation.place}</Typography>
                <Typography variant="body2" color="textSecondary">
                  {updatedLocation.state}, {updatedLocation.country}
                </Typography>
              </Box>
            </div>
          )}
        </CardContent>
      </Card>
    </div>
  );
}

export default AdminAirportItem;
