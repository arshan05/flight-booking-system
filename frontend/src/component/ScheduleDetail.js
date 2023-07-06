import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faPlaneDeparture,
  faPlaneArrival,
  faTicket,
  faClock,
  faPaperPlane,
} from "@fortawesome/free-solid-svg-icons";
import dayjs from "dayjs";
import { Button, FormLabel, Icon, IconButton } from "@mui/material";
import { FormText } from "react-bootstrap";
import Payment from "./Payment";
import { useDispatch, useSelector } from "react-redux";

const ScheduleDetail = (props) => {
  

  const sharedState = props.sharedState;

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
    <div>
      <div className="d-flex flex-column justify-content-center">
        <div className="card p-3 col-12">
          <h5 className="card-title">Flight Details</h5>
          <div className="card-body">
            <div className="d-flex flex-column justify-content-around">
              <span
                className="fs-4"
                style={{
                  fontWeight: "bold",
                  fontFamily: "sans-serif",
                  fontSize: "20px",
                  color: "#142c54",
                }}
              >
                {airlineName}
              </span>

              <span>{flightNumber}</span>

              <div>
                <table className="table table-striped">
                  <tbody>
                  <tr>
                    <td>
                      <FontAwesomeIcon
                        icon={faPlaneDeparture}
                        style={{ fontSize: "20px", color: "#142c54" }}
                      />
                    </td>
                    <td>{boarding}</td>
                    <td>
                      <span style={{ fontSize: "16px" }} className="text-muted">
                        {startTime.format("HH:mm")}
                      </span>
                    </td>
                  </tr>

                  <tr>
                    <td>
                      <FontAwesomeIcon
                        icon={faPlaneArrival}
                        style={{ fontSize: "20px", color: "#142c54" }}
                      />
                    </td>
                    <td>{destination}</td>
                    <td>
                      <span style={{ fontSize: "16px" }} className="text-muted">
                        {endTime.format("HH:mm")}
                      </span>
                    </td>
                  </tr>

                  <tr>
                    <td>
                      <FontAwesomeIcon
                        icon={faClock}
                        style={{ fontSize: "20px", color: "#142c54" }}
                      />
                    </td>
                    <td>
                      {duration.hrs}:{duration.mins}
                    </td>
                    
                    <td>
                      
                    </td>
                    
                  </tr>
                  </tbody>
                </table>

                <hr></hr>
                <div>
                  <div className="d-flex justify-content-between">
                    <FormLabel>Amount</FormLabel>
                    <p style={{ fontWeight: "bold", margin: 0 }}>
                      &#8377; {sharedState.sharedSeat.price}
                    </p>
                  </div>

                  {/* <hr></hr> */}
                  <div className="d-flex justify-content-between">
                    <FormLabel>Seat</FormLabel>
                    <p style={{ fontWeight: "bold", margin:0 }}>
                      {sharedState.sharedSeat.seatNumber},{" "}
                      {sharedState.sharedSide}
                    </p>
                  </div>
                  {/* <hr></hr> */}
                  <div className="d-flex justify-content-between align-items-center">
                    <FormLabel>Class</FormLabel>
                    <p style={{ fontWeight: "bold", margin:0 }}>
                      {sharedState.sharedSeat.className}
                    </p>
                  </div>
                  <hr></hr>
                </div>

                <div>
                  <Payment sharedState={sharedState}/>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ScheduleDetail;
