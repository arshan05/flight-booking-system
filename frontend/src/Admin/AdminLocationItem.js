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
import { useDispatch } from "react-redux";
import { deleteLocation, updateLocation } from "../service/LocationService";

function AdminLocationItem(props) {
  const dispatch = useDispatch();
  const [editMode, setEditMode] = useState(false);
  const [openDeleteDialog, setOpenDeleteDialog] = useState(false);

  const place = props.location.place;
  const state = props.location.state;
  const country = props.location.country;

  const [updatedplace, setUpdatedPlace] = useState(place);
  const [updatedState, setUpdatedState] = useState(state);
  const [updatedCountry, setUpdatedCountry] = useState(country);

  const handleDelete = () => {
    setOpenDeleteDialog(false);
    console.log(props.location.id);
    dispatch(deleteLocation(props.location));
  };

  const handleUpdate = () => {
    const updatedLocation = {
      id: props.location.id,
      place: updatedplace,
      state: updatedState,
      country: updatedCountry,
    };
    dispatch(updateLocation(updatedLocation));
    setEditMode(false);
  };

  return (
    <div>
      <Card className="m-3">
        <CardContent className="card-body">
          {editMode && (
            <div className="m-3">
              <div className="my-1">
                <FormLabel>Place:</FormLabel>
                <TextField
                  margin="none"
                  fullWidth
                  id="place"
                  type="text"
                  value={updatedplace}
                  required
                  variant="standard"
                  onChange={(event) => {
                    setUpdatedPlace(event.target.value);
                  }}
                />
              </div>

              <div className="my-1">
                <FormLabel>State:</FormLabel>
                <TextField
                  margin="none"
                  fullWidth
                  value={updatedState}
                  id="state"
                  type="text"
                  required
                  variant="standard"
                  onChange={(event) => {
                    setUpdatedState(event.target.value);
                  }}
                />
              </div>

              <div className="my-1">
                <FormLabel>Country:</FormLabel>
                <TextField
                  margin="none"
                  fullWidth
                  value={updatedCountry}
                  id="country"
                  type="text"
                  required
                  variant="standard"
                  onChange={(event) => {
                    setUpdatedCountry(event.target.value);
                  }}
                />
              </div>
            </div>
          )}

          {!editMode && (
            <div className="m-3">
              <FormLabel>Place:</FormLabel>
              <p className="text fs-6">{place}</p>

              <FormLabel>State:</FormLabel>
              <p className="text fs-6">{state}</p>

              <FormLabel>Country:</FormLabel>
              <p className="text fs-6">{country}</p>
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
              <Button onClick={handleUpdate}>Edit</Button>
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

export default AdminLocationItem;
