import {
  Breadcrumbs,
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  Tooltip,
  Typography,
} from "@mui/material";
import React, { useState } from "react";
import "../style/schedule.css";
import { useDispatch, useSelector } from "react-redux";
import { allBookingsActions } from "../store/all-bookings-slice";
import { cancelTicket, checkinPassenger } from "../service/AllBookingsService";
import dayjs from "dayjs";
import { useNavigate } from "react-router-dom";
import BoardingPass from "./BoardingPass";
import { Modal } from "react-bootstrap";
const BookingItem = (props) => {
  const isCheckedin = props.booking.checkedIn;
  const [isOpen, setOpen] = useState(false);
  const [isOpenWebCheckin, setOpenWebCheckin] = useState(false);
  const [isOpenPass, setOpenPass] = useState(false);

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const booking = props.booking;
  // console.log(booking);

  const boardingLocationCode = booking.schedule.boarding.code;
  const boardingAirportName = props.booking.schedule.boarding.airportName;
  const boardingPlace = booking.schedule.boarding.location.place;

  const destinationLocationCode = booking.schedule.destination.code;
  const destinationAirportName = booking.schedule.destination.airportName;
  const destinationPlace = booking.schedule.destination.location.place;

  const boardingDate = dayjs(booking.schedule.startTime);
  const landingDate = dayjs(booking.schedule.endTime);

  console.log(boardingDate);

  const airline = booking.schedule.flight.airlineCompany.airlineName;
  const flightNumber = booking.schedule.flight.flightNumber;
  const seatNumber = booking.seatNumber;

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
          <Button
            onClick={() => {
              console.log(booking);
              dispatch(cancelTicket(booking));
              setOpen(false);
            }}
            className="bg-danger text-white"
            autoFocus
          >
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
              <p className="fs-3">{boardingLocationCode}</p>
            </div>
            <div className="d-flex flex-column align-items-center text-center justify-content-center">
              <p className="fs-6 text-muted">{boardingAirportName}</p>
              <p className="fs-6 text-muted">{boardingPlace}</p>
            </div>
          </div>
          <div className="col-4 ">
            <div className="d-flex justify-content-center">
              <p className="fs-3">{destinationLocationCode}</p>
            </div>
            <div className="d-flex flex-column align-items-center text-center justify-content-center">
              <p className="fs-6 text-muted">{destinationAirportName}</p>
              <p className="fs-6 text-muted">{destinationPlace}</p>
            </div>
          </div>
        </div>

        <div className="d-flex flex-row justify-content-around">
          <Breadcrumbs
            separator={<span style={{ color: "navy" }}>‹›</span>}
            aria-label="Breadcrumb"
          >
            <Tooltip title="Boarding Date" placement="top">
              <Typography variant="body1">
                {boardingDate.format("MMMM D, YYYY")}
              </Typography>
            </Tooltip>

            <Tooltip title="Boarding Time" placement="top">
              <Typography variant="body1">
                {boardingDate.$H}:{boardingDate.$m}
              </Typography>
            </Tooltip>

            <Tooltip title="Airline" placement="top">
              <Typography variant="body1">{airline}</Typography>
            </Tooltip>

            <Tooltip title="Flight Number" placement="top">
              <Typography variant="body1">{flightNumber}</Typography>
            </Tooltip>

            <Tooltip title="Seat Number" placement="top">
              <Typography variant="body1">{seatNumber}</Typography>
            </Tooltip>

            <Tooltip title="Landing Time" placement="top">
              <Typography variant="body1">
                {landingDate.$H}:{landingDate.$m}
              </Typography>
            </Tooltip>

            <Tooltip title="Landing Date" placement="top">
              <Typography variant="body1">
                {landingDate.format("MMMM D, YYYY")}
              </Typography>
            </Tooltip>
          </Breadcrumbs>
        </div>

        <div className="m-3 d-flex flex-row justify-content-around">
          {!isCheckedin && (
            <div className="">
              <Button
                variant="contained"
                className="item"
                onClick={() => {
                  dispatch(checkinPassenger(booking.pnr));
                  setOpenWebCheckin(true);
                }}
              >
                web-checkin
              </Button>
            </div>
          )}

          {isCheckedin && (
            <div className="">
              <Button
                variant="contained"
                className="item"
                onClick={() => {
                  setOpenPass(true);
                }}
              >
                boarding pass
              </Button>
              <Modal
              size="xl"
              container={document.body}
              className="modal-xl"
                show={isOpenPass}
                onHide={() => {setOpenPass(false)}}
                dialogClassName="modal-full-width"
              >
                <Modal.Header closeButton>
                  <Modal.Title>Boarding Pass</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                  <div className="">
                  <BoardingPass booking={booking} />
                  </div>
                </Modal.Body>
              </Modal>
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
