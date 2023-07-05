import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faPlaneDeparture,
  faPlaneArrival,
  faClock,
} from "@fortawesome/free-solid-svg-icons";
import dayjs from "dayjs";

const ScheduleDetail = (props) => {
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
                  <tr>
                    <td>
                      <FontAwesomeIcon
                        icon={faPlaneDeparture}
                        style={{ fontSize: "20px", color: "#142c54" }}
                      />
                    </td>
                    <td>{boarding}</td>
                    <td>-</td>
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
                    <td>-</td>
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
                  </tr>
                </table>

                <hr>
                </hr>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ScheduleDetail;
