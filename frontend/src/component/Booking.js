import { useLocation } from "react-router-dom";
import AddPassenger from "./AddPassenger";
import ScheduleDetail from "./ScheduleDetail";
import Seat from "./Seat";
import { getSeatClass } from "../service/SeatService";
import { useState } from "react";

const Booking = () => {
  const location = useLocation();
  const scheduleDetails = location.state;
  const [sharedSeatFare,setSharedSeatFare] = useState(0);
  
  const handleSharedSeatFareChange = (newFare) => {
    setSharedSeatFare(newFare);
  }
  console.log("shared seat fare: " + sharedSeatFare);
  
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
            <Seat schedule={scheduleDetails} onSharedSeatFareChanged={handleSharedSeatFareChange} sharedSeatFare={sharedSeatFare}/>
          </div>
        </div>

        {/* schedule details */}
        <div className="col col-4 m-5">
            <ScheduleDetail schedule={scheduleDetails} sharedSeatFare={sharedSeatFare}/>
        </div>
      </div>
  );
};

export default Booking;
