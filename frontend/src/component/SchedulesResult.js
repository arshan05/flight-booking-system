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
      <ul>
        
        {schedulesResult !== undefined && schedulesResult.map((schedule) => (
          <Schedule schedule={schedule} />
        ))}

        {schedulesResult === undefined && <NoResultsFound/>}

      </ul>
    </div>
  );
};

export default SchedulesResult;
