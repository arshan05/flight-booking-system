import {
  Breadcrumbs,
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  Tooltip,
  Typography
} from "@mui/material";
import React, { useState } from "react";
import "../style/schedule.css";
const BookingItem = () => {
  const isCheckedin = false;
  const [isOpen, setOpen] = useState(false);
  const [isOpenWebCheckin, setOpenWebCheckin] = useState(false);
  return (
    <li className="d-flex justify-content-center">
      <Dialog
        // TransitionComponent={Transition}
        open={isOpen}
        onClose={() => {
          setOpen(false);
        }}
      >
        <DialogTitle>Cancel Ticket Confirmation</DialogTitle>
        <DialogContent>
          <p>Are you sure you want to cancel the ticket?</p>
        </DialogContent>
        <DialogActions>
          <Button
            onClick={() => {
              setOpen(false);
            }}
            color="primary"
          >
            Dismiss
          </Button>
          <Button onClick={() => {}} className="bg-danger text-white" autoFocus>
            Cancel Ticket
          </Button>
        </DialogActions>
      </Dialog>

      <Dialog
        // TransitionComponent={Transition}
        open={isOpenWebCheckin}
        onClose={() => {
          setOpenWebCheckin(false);
        }}
      >
        <DialogTitle>Web-Checkin</DialogTitle>
        <DialogContent>
          <p>You have successfully checked in🎉</p>
        </DialogContent>
        <DialogActions>
          <Button
            onClick={() => {
              setOpenWebCheckin(false);
            }}
            color="primary"
          >
            okay
          </Button>
          
        </DialogActions>
      </Dialog>

      <div
        className="card m-5 p-4 w-75"
        style={{ backgroundColor: "ghostwhite" }}
      >
        <div className="d-flex flex-row justify-content-around">
          <div className="col-4">
            <div className="d-flex justify-content-center">
              <p className="fs-3">BLR</p>
            </div>
            <div className="d-flex justify-content-center">
              <p className="fs-6 text-muted">Kempegowda Intl. Airport</p>
            </div>
          </div>
          <div className="col-4 ">
            <div className="d-flex justify-content-center">
              <p className="fs-3">DEL</p>
            </div>
            <div className="d-flex justify-content-center">
              <p className="fs-6 text-muted">Indra Intl. Airport</p>
            </div>
          </div>
        </div>

        <div className="d-flex flex-row justify-content-around">
          <Breadcrumbs
            separator={<span style={{ color: "navy" }}>‹›</span>}
            aria-label="Breadcrumb"
          >
            <Tooltip title="Boarding Date" placement="top">
              <Typography variant="body1">July 12, 2023</Typography>
            </Tooltip>

            <Tooltip title="Boarding Time" placement="top">
              <Typography variant="body1">10:30</Typography>
            </Tooltip>

            <Tooltip title="Airline" placement="top">
              <Typography variant="body1">Air One</Typography>
            </Tooltip>

            <Tooltip title="Flight Number" placement="top">
              <Typography variant="body1">FL001</Typography>
            </Tooltip>

            <Tooltip title="Seat Number" placement="top">
              <Typography variant="body1">15A</Typography>
            </Tooltip>

            <Tooltip title="Landing Time" placement="top">
              <Typography variant="body1">12:40</Typography>
            </Tooltip>

            <Tooltip title="Landing Date" placement="top">
              <Typography variant="body1">July 12, 2023</Typography>
            </Tooltip>
          </Breadcrumbs>
        </div>

        <div className="m-3 d-flex flex-row justify-content-around">
          {!isCheckedin && (
            <div className="">
              <Button variant="contained" className="item" onClick={() => {
                  setOpenWebCheckin(true);
                }}>
                web-checkin
              </Button>
            </div>
          )}

          {isCheckedin && (
            <div className="">
              <Button variant="contained" className="item">
                boarding pass
              </Button>
            </div>
          )}

          {!isCheckedin && (
            <div>
              <Button
                variant="contained"
                className="bg-danger item"
                onClick={() => {
                  setOpen(true);
                }}
              >
                cancel ticket
              </Button>
            </div>
          )}
        </div>
      </div>
    </li>
  );
};

export default BookingItem;
