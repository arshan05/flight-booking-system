import React, { useState } from "react";
import paymentService from "../service/PaymentService";
import dayjs from "dayjs";
import { Button } from "@mui/material";
import { useSelector } from "react-redux";

const Schedule = (props) => {
  const auth = useSelector((state) => state.auth);

  const airlineName = props.schedule?.flight.airlineCompany.airlineName;
  const flightNumber = props.schedule?.flight.flightNumber;
  const startTime = dayjs(props.schedule?.startTime);

  const boarding = props.schedule?.boarding.location.place;

  const endTime = dayjs(props.schedule?.endTime);

  const diffMin = endTime.diff(startTime, "minute");
  const duration = {
    hrs: String(Math.floor(diffMin / 60)).padStart(2, "0"),
    mins: String(diffMin % 60).padStart(2, "0"),
  };

  const destination = props.schedule?.destination.location.place;
  const price = props.schedule?.baseFare;

  return (
    <li>
      <div className="card m-5 p-2 d-flex flex-row justify-content-around">
        {/* flight name & number */}
        <div>
          <div>
            {/* {console.log(props.schedules)} */}
            <p className="fs-4 fw-bold">{airlineName}</p>
          </div>
          <div>
            <p className="fs-6">{flightNumber}</p>
          </div>
        </div>
        {/* start timing & place */}
        <div>
          <div>
            <p className="fs-4 fw-bold">{startTime.format("HH:mm")}</p>
          </div>
          <div>
            <p className="fs-6">{boarding}</p>
          </div>
        </div>
        {/* duration */}
        <div>
          <div>
            <p className="fs-4 fw-bolder">
              {duration.hrs}:{duration.mins}
            </p>
          </div>
          <div>
            <p className="fs-6">Non stop</p>
          </div>
        </div>
        {/* end & place */}
        <div>
          <div>
            <p className="fs-4 fw-bold">{endTime.format("HH:mm")}</p>
          </div>
          <div>
            <p className="fs-6">{destination}</p>
          </div>
        </div>

        {/* price */}
        <div>
          <div>
            <p className="fs-4 fw-bold">&#8377; {price}</p>
          </div>
          <div>
            <p className="fs-6">per person</p>
          </div>
        </div>

        {auth.isAuthenticated && (
          <div className="d-flex align-items-center justify-content-center">
            <div>
              <Button
                variant="contained"
                style={{ backgroundColor: "#142c54" }}
              >
                Book
              </Button>
            </div>
          </div>
        )}
      </div>
    </li>
  );
};

export default Schedule;
