import { useState } from "react";
import ScheduleService from "../service/ScheduleService";
import ConsumerService from "../service/ConsumerService.js";

const myStyle = {
  backgroundColor: "#f5f5f5",
  color: "black",
  padding: "10px",
  fontSize: "16px",
  borderRadius: "5px",
  opacity:1
};

const Home = () => {
  // ScheduleService.getAllSchedules();

  const [fromField, setFromField] = useState('');
  const [toField, setToField] = useState('');
  const [dateField, setDateField] = useState('');

  const submitHandler = (event) => {
    event.preventDefault();
    const flightDetails = {
      'start': fromField,
      'destination': toField,
      'date': dateField
    }; 

  //   const flightDetailsRequest  = {
  //     "start":"Bengaluru", 
  //     "destination":"New Delhi", 
  //     "date":"2023-06-07"
  // }
    console.log(flightDetails);
    ConsumerService.getFlights(flightDetails);
  }

  return (
    <div style={{
      backgroundImage:
        "url(https://images.pexels.com/photos/62623/wing-plane-flying-airplane-62623.jpeg?auto=compress&cs=tinysrgb&w=1600)",
      backgroundSize: "cover",
      backgroundRepeat: "no-repeat",
      backgroundPosition: "center",
    }}>
    <div
      className="d-flex justify-content-center align-items-end"
    >
      <div className="card m-5 p-3 col-lg-6 col-sm-10 bg-opacity-50" style={myStyle}>
        <h5 className="card-title">Flight Details</h5>
        <div className="card-body">
          <form onSubmit={submitHandler}>
            <div className="mb-3 d-flex flex-row justify-content-around">
              <div>
                <label htmlFor="fromAddress" className="form-label">
                  From
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="fromAddress"
                  onChange={ (event) => {setFromField(event.target.value)}}
                  placeholder="Enter from address"
                  required
                />
              </div>
              <div>
                <label htmlFor="toAddress" className="form-label">
                  To
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="toAddress"
                  onChange={ (event) => {setToField(event.target.value)}}
                  placeholder="Enter to address"
                  required
                />
              </div>
              <div>
                <label htmlFor="datePicker" className="form-label">
                  Date
                </label>
                <input
                  type="date"
                  className="form-control"
                  id="datePicker"
                  onChange={ (event) => {setDateField(event.target.value)}}
                  required
                />
              </div>
            </div>
            <div className="d-flex flex-row justify-content-center">
              <button
                type="submit"
                className="btn"
                style={{ backgroundColor: "#142c54", color: "white" }}
              >
                Search Flights
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
    </div>
  );
};

export default Home;
