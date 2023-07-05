import React from "react";

import dayjs from "dayjs";
import { Button } from "@mui/material";
import { useSelector } from "react-redux";

import "../style/schedule.css";
import { useNavigate } from "react-router-dom";

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

  const navigate = useNavigate();
  const bookHandler = () => {
    navigate("/booking", {state:props.schedule});
  };

  return (
    <li>
      <div className="card m-5 p-2 d-flex flex-row justify-content-around">
        <div className="d-flex align-items-center justify-content-center">
          <div>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="50"
              height="50"
              fill="#142c54"
              class="bi bi-airplane-fill"
              viewBox="0 0 16 16"
            >
              <path d="M6.428 1.151C6.708.591 7.213 0 8 0s1.292.592 1.572 1.151C9.861 1.73 10 2.431 10 3v3.691l5.17 2.585a1.5 1.5 0 0 1 .83 1.342V12a.5.5 0 0 1-.582.493l-5.507-.918-.375 2.253 1.318 1.318A.5.5 0 0 1 10.5 16h-5a.5.5 0 0 1-.354-.854l1.319-1.318-.376-2.253-5.507.918A.5.5 0 0 1 0 12v-1.382a1.5 1.5 0 0 1 .83-1.342L6 6.691V3c0-.568.14-1.271.428-1.849Zm.894.448C7.111 2.02 7 2.569 7 3v4a.5.5 0 0 1-.276.447l-5.448 2.724a.5.5 0 0 0-.276.447v.792l5.418-.903a.5.5 0 0 1 .575.41l.5 3a.5.5 0 0 1-.14.437L6.708 15h2.586l-.647-.646a.5.5 0 0 1-.14-.436l.5-3a.5.5 0 0 1 .576-.411L15 11.41v-.792a.5.5 0 0 0-.276-.447L9.276 7.447A.5.5 0 0 1 9 7V3c0-.432-.11-.979-.322-1.401C8.458 1.159 8.213 1 8 1c-.213 0-.458.158-.678.599Z" />
            </svg>
          </div>
        </div>

        {/* flight name & number */}
        <div className="d-flex flex-column align-items-center justify-content-center">
          <div>
            {/* {console.log(props.schedules)} */}
            <p className="fs-4 fw-bold">{airlineName}</p>
          </div>
          <div>
            <p className="fs-6">{flightNumber}</p>
          </div>
        </div>
        {/* start timing & place */}
        <div className="d-flex flex-column align-items-center justify-content-center">
          <div>
            <p className="fs-4 fw-bold">{startTime.format("HH:mm")}</p>
          </div>
          <div>
            <p className="fs-6">{boarding}</p>
          </div>
        </div>
        {/* duration */}
        <div className="d-flex flex-column align-items-center justify-content-center">
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
        <div className="d-flex flex-column align-items-center justify-content-center">
          <div>
            <p className="fs-4 fw-bold">{endTime.format("HH:mm")}</p>
          </div>
          <div>
            <p className="fs-6">{destination}</p>
          </div>
        </div>

        {/* price */}
        <div className="d-flex flex-column align-items-center justify-content-center">
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
                className="item"
                variant="contained"
                style={{ backgroundColor: "#142c54" }}
                onClick={bookHandler}
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
