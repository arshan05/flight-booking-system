import React from "react";
import { Card, CardContent, Grid, Typography } from "@mui/material";
import dayjs from "dayjs";
import { useLocation } from "react-router-dom";
import { FormLabel } from "react-bootstrap";
import { useSelector } from "react-redux";
import "../style/style.css";
const BoardingPass = (props) => {
//   const location = useLocation();
//   const booking = location.state;

const booking = props.booking;

  const bookedSchedule = useSelector((state) => state.bookedSchedule);

  console.log(booking);


  const boardingLocationCode = booking.schedule.boarding.code;
  const boardingAirportName = booking.schedule.boarding.airportName;
  const boardingPlace = booking.schedule.boarding.location.place;

  const destinationLocationCode = booking.schedule.destination.code;
  const destinationAirportName = booking.schedule.destination.airportName;
  const destinationPlace = booking.schedule.destination.location.place;

  const boardingDate = dayjs(booking.schedule.startTime);
  const landingDate = dayjs(booking.schedule.endTime);

  const pnrNumber = booking.pnr;

  const passengerName = booking.passenger.name;

  console.log(boardingDate);

  const airline = booking.schedule.flight.airlineCompany.airlineName;
  const flightNumber = booking.schedule.flight.flightNumber;
  const seatNumber = booking.seatNumber;

  const seatClass = booking.schedule.fare.find(
    (seat) => seat.seatNumber === seatNumber
  ).className;

  const width = window.innerWidth;
  const cardWidth = width > 768 ? "55%" : "100%";

  return (
    <div className=" d-flex justify-content-center">
      <div
        className="card shadow-lg rounded col-12 col-md-11 col-lg-6 w-100"
        style={{ backgroundColor: "ghostwhite" }}
      >
        <div className="d-flex flex-row align-items-center">
          <div className="col-8">
            <div className="row m-3">
              <div className="col d-flex justify-content-around">
                <div className="d-flex flex-column align-items-center justify-content-center">
                  <p className="fs-3">{boardingLocationCode}</p>
                  <p className="fs-6">{boardingPlace.toUpperCase()}</p>
                </div>
                <div className="d-flex flex-column align-items-center justify-content-center">
                  <p className="fs-3">{destinationLocationCode}</p>
                  <p className="fs-6">{destinationPlace.toUpperCase()}</p>
                </div>
              </div>
            </div>
            <div className="d-flex justify-content-center mx-4">
              <table className="table ">
                <tbody className="m-3">
                  <tr>
                    <td>
                      <div className="d-flex flex-column justify-content-around">
                        <p
                          className="fs-6 text-muted"
                          style={{ margin: "0px" }}
                        >
                          FLIGHT NO.
                        </p>
                        <p
                          className="fs-5"
                          style={{ margin: "0px", color: "#142c54" }}
                        >
                          {flightNumber}
                        </p>
                      </div>
                    </td>
                    <td>
                      <div className="d-flex flex-column justify-content-around">
                        <p
                          className="fs-6 text-muted"
                          style={{ margin: "0px" }}
                        >
                          PASSENGER
                        </p>
                        <p
                          className="fs-5"
                          style={{ margin: "0px", color: "#142c54" }}
                        >
                          {passengerName.toUpperCase()}
                        </p>
                      </div>
                    </td>
                    <td>
                      <div className="d-flex flex-column justify-content-around">
                        <p
                          className="fs-6 text-muted"
                          style={{ margin: "0px" }}
                        >
                          PNR
                        </p>
                        <p
                          className="fs-5"
                          style={{ margin: "0px", color: "#142c54" }}
                        >
                          {pnrNumber}
                        </p>
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <div className="d-flex flex-column justify-content-around">
                        <p
                          className="fs-6 text-muted"
                          style={{ margin: "0px" }}
                        >
                          DATE
                        </p>
                        <p
                          className="fs-5"
                          style={{ margin: "0px", color: "#142c54" }}
                        >
                          {boardingDate.format("DD MMM, YYYY")}
                        </p>
                      </div>
                    </td>
                    <td>
                      <div className="d-flex flex-column justify-content-around">
                        <p
                          className="fs-6 text-muted"
                          style={{ margin: "0px" }}
                        >
                          DEPARTS
                        </p>
                        <p
                          className="fs-5"
                          style={{ margin: "0px", color: "#142c54" }}
                        >
                          {boardingDate.$H} : {boardingDate.$m}
                        </p>
                      </div>
                    </td>
                    <td>
                      <div className="d-flex flex-column justify-content-around">
                        <p
                          className="fs-6 text-muted"
                          style={{ margin: "0px" }}
                        >
                          ARRIVES
                        </p>
                        <p
                          className="fs-5"
                          style={{ margin: "0px", color: "#142c54" }}
                        >
                          {landingDate.$H} : {landingDate.$m}
                        </p>
                      </div>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <div className="col-4" style={{ backgroundColor: "#142c54" }}>
            <div className="row">
              <div className="col">
                <div className="col p-4">
                  <p style={{ color: "lightsteelblue", margin: "0px" }}>
                    BOARDING
                  </p>
                  <FormLabel
                    className="fs-4"
                    style={{ color: "whitesmoke", margin: "0px" }}
                  >
                    {boardingDate.$H - 1}:{boardingDate.$m}
                  </FormLabel>
                </div>

                <div className="col p-4">
                  <p style={{ color: "lightsteelblue", margin: "0px" }}>SEAT</p>
                  <FormLabel
                    className="fs-4"
                    style={{ color: "whitesmoke", margin: "0px" }}
                  >
                    {seatNumber}
                  </FormLabel>
                </div>

                <div className="col p-4">
                  <p style={{ color: "lightsteelblue", margin: "0px" }}>
                    CLASS
                  </p>
                  <FormLabel
                    className="fs-4"
                    style={{ color: "whitesmoke", margin: "0px" }}
                  >
                    {seatClass}
                  </FormLabel>
                </div>
              </div>

              <div className="col">
                <div className="d-flex h-100 flex-column justify-content-center align-items-end">
                  <p
                    className="barcode-font fs-1"
                    variant="body1"
                    style={{
                      margin: "0px",
                      color: "whitesmoke",
                      writingMode: "vertical-rl",
                      textOrientation: "mixed",
                      transform: "rotate(180deg)",
                    }}
                  >
                    Vertical Text
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default BoardingPass;
