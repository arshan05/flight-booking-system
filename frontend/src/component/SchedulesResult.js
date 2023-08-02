import { useDispatch, useSelector } from "react-redux";
import Schedule from "./Schedule";
import { useEffect } from "react";
import { useLocation } from "react-router-dom";
import { getFlights } from "../service/ConsumerService";
import NoResultsFound from "./NoResultsFound";

const SchedulesResult = () => {
  const dispatch = useDispatch();
  const location = useLocation();
  const schedulesResult = useSelector(
    (state) => state.schedulesResult.schedulesResult
  );

  const flightDetails = location.state;
  useEffect(() => {
    dispatch(getFlights(flightDetails));
    console.log(schedulesResult);
  }, [dispatch]);
  return (
    <div>
      <ul style={{ listStyleType: "none" }}>
        {schedulesResult === undefined || schedulesResult === null ? (
          <NoResultsFound />
        ) : Array.isArray(schedulesResult) ? (
          schedulesResult.map((schedule) => <Schedule schedule={schedule} />)
        ) : (
          <NoResultsFound />
        )}
      </ul>

      {/* <ul style={{ listStyleType: "none" }}>
        {(schedulesResult === undefined || schedulesResult === null) && <NoResultsFound />}

        {schedulesResult !== undefined &&
          schedulesResult.map((schedule) => <Schedule schedule={schedule} />)}
      </ul> */}

      <div
        className="footer d-flex align-items-end"
        style={{ height: "50vh", width: "100%"}}
      >
        <img
          className="img-fluid"
          src="https://cdn.dribbble.com/userupload/4210795/file/original-5233c93eb5f28c04fcb308b8b70c380f.png?resize=1200x900"
          style={{
            width: "100%",
            height: "100%",
            objectFit: "contain",
          }}
          alt="Footer Image"
        />
      </div>
    </div>
  );
};

export default SchedulesResult;
