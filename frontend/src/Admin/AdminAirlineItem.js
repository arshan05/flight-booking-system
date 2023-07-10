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
import { deleteAirline, updateAirline } from "../service/AirlineService";

function AdminAirlineItem(props) {
  const dispatch = useDispatch();
  const [editMode, setEditMode] = useState(false);
  const [openDeleteDialog, setOpenDeleteDialog] = useState(false);

  const airlineName = props.airline.airlineName;
  const code = props.airline.code;

  const [updatedAirlineName, setUpdatedAirlineName] = useState(airlineName);
  const [updatedCode, setUpdatedCode] = useState(code);

  const handleDelete = () => {
    setOpenDeleteDialog(false);
    dispatch(deleteAirline(props.airline));
  };

  const handleUpdate = () => {
    const updatedAirline = {
      id: props.airline.id,
      airlineName: updatedAirlineName,
      code: updatedCode,
    };
    dispatch(updateAirline(updatedAirline));
    setEditMode(false);
  };

  return (
    <div>
      <Card className="m-3">
        <CardContent className="card-body">
          {editMode && (
            <div className="m-3">
              <div className="my-1">
                <FormLabel>Airline Name:</FormLabel>
                <TextField
                  margin="none"
                  fullWidth
                  id="airlineName"
                  type="text"
                  value={updatedAirlineName}
                  required
                  variant="standard"
                  onChange={(event) => {
                    setUpdatedAirlineName(event.target.value);
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
            </div>
          )}

          {!editMode && (
            <div className="m-3">
              <FormLabel>Airline Name:</FormLabel>
              <p className="text fs-6">{airlineName}</p>

              <FormLabel>Code:</FormLabel>
              <p className="text fs-6">{code}</p>

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

export default AdminAirlineItem;
