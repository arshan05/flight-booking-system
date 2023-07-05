import { useLocation } from "react-router-dom";
import AddPassenger from "./AddPassenger";
import ScheduleDetail from "./ScheduleDetail";
import Seat from "./Seat";
import { getSeatClass } from "../service/SeatService";

const Booking = () => {
  const location = useLocation();
  const scheduleDetails = location.state;
  console.log(getSeatClass(scheduleDetails.fare));
  return (
      <div className="d-flex flex-row justify-content-center align-items">
        <div className="col col-6 m-5">
          {/* passenger details */}
          <div className="row">
            <AddPassenger/>
          </div>

        <hr></hr>
          {/* booking details */}
          <div className="row">
            <Seat/>
          </div>
        </div>

        {/* schedule details */}
        <div className="col col-4 m-5">
            <ScheduleDetail schedule={scheduleDetails}/>
        </div>
      </div>
  );
};

export default Booking;
