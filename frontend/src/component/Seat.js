import {
  FormControlLabel,
  MenuItem,
  Radio,
  RadioGroup,
  Select,
  TextField,
  FormLabel,
} from "@mui/material";
import { getAvailableSeats, getSeatClass } from "../service/SeatService";
import { useState } from "react";

const Seat = (props) => {
  const schedule = props.schedule;
  const sharedSeatFare = props.sharedSeatFare;
  const onSharedSeatFareChanged = props.onSharedSeatFareChanged;

  const options = Array.from(getSeatClass(schedule.fare));
  const sides = ["WINDOW", "MIDDLE", "AISLE"];

  const [selectedClass, setSelectedClass] = useState(options[0]);
  const [selectedSide, setSelectedSide] = useState(sides[0]);
  const [availableSeatsForSide, setAvailableSeatsForSide] = useState(
    getAvailableSeats(selectedClass, selectedSide, schedule.fare)
  );
  const [selectedSeat, setSelectedSeat] = useState(availableSeatsForSide[0]);
  const [seatFare, setSeatFare] = useState(selectedSeat.price);
  onSharedSeatFareChanged(selectedSeat.price);

  console.log(seatFare);
  const handleSelectedClassChange = (event) => {
    setSelectedClass(event.target.value);
    const temp2 = getAvailableSeats(
      event.target.value,
      selectedSide,
      schedule.fare
    );
    setAvailableSeatsForSide(temp2);
    setSelectedSeat(temp2[0]);
    setSeatFare(temp2[0].price);
    onSharedSeatFareChanged(temp2[0].price);
  };

  const handleSelectedSideChange = (event) => {
    setSelectedSide(event.target.value);
    const temp1 = getAvailableSeats(
      selectedClass,
      event.target.value,
      schedule.fare
    );
    setAvailableSeatsForSide(temp1);
    setSelectedSeat(temp1[0]);
    setSeatFare(temp1[0].price);
    onSharedSeatFareChanged(temp1[0].price);
  };

  const handleSelectedSeatChange = (event) => {
    const tempSeat = availableSeatsForSide.find(
      (seat) => seat.seatNumber === event.target.value
    );
    setSelectedSeat(tempSeat);
    setSeatFare(tempSeat.price);
    onSharedSeatFareChanged(tempSeat.price);
  };

  // const options = Array.from(getSeatClass(schedule.fare));
  // const [selectedClass, setSelectedClass] = useState(options[0]);
  // const handleSelectedClassChange = (event) => {
  //   setSelectedClass(event.target.value);
  // };

  // const [availableSeatsForSide,setAvailableSeatsForSide] = useState(getAvailableSeats(selectedClass, "WINDOW",schedule.fare));

  // const side = ["Window", "Middle", "Aisle"];
  // const [selectedSide, setSelectedSide] = useState(side[0]);
  // const handleSelectedSideChange = (event) => {
  //   setSelectedSide(event.target.value);
  //   setAvailableSeatsForSide(getAvailableSeats(selectedClass, selectedSide,schedule.fare))
  // };

  // const availableSeats = getAvailableSeats(
  //   selectedClass,
  //   selectedSide.toUpperCase(),
  //   schedule.fare
  // );

  // const [seatFare, setSeatFare] = useState(availableSeatsForSide[0].price);

  // const [selectedSeat, setSelectedSeat] = useState(
  //   availableSeatsForSide[0].seatNumber
  // );
  // const handleSelectedSeatChange = (event) => {
  //   setSelectedSeat(event.target.value);
  //   const tempSeat = availableSeats.find(seat => seat.seatNumber === event.target.value);

  //   setSeatFare(tempSeat.price);
  // };
  // console.log(seatFare);
  return (
    <div>
      <div className="d-flex felx-column justify-content-center">
        <div className="card p-3 col-12">
          <h5 className="card-title">Seat</h5>
          <div className="card-body">
            <form>
              <div className="d-flex flex-column justify-content-around">
                <div>
                  <FormLabel margin="normal">Class</FormLabel>
                  <RadioGroup
                    row
                    value={selectedClass}
                    onChange={handleSelectedClassChange}
                  >
                    {options.map((option) => (
                      <FormControlLabel
                        key={option}
                        value={option}
                        control={<Radio />}
                        label={option.charAt(0) + option.slice(1).toLowerCase()}
                      />
                    ))}
                  </RadioGroup>
                </div>

                <div>
                  <FormLabel margin="normal">Side</FormLabel>
                  <RadioGroup
                    row
                    value={selectedSide}
                    onChange={handleSelectedSideChange}
                  >
                    {sides.map((option) => (
                      <FormControlLabel
                        key={option}
                        value={option}
                        control={<Radio />}
                        label={option.charAt(0) + option.slice(1).toLowerCase()}
                      />
                    ))}
                  </RadioGroup>
                </div>

                <div>
                  <FormLabel margin="normal">Select Seat</FormLabel>

                  <Select
                    margin="normal"
                    fullWidth
                    value={selectedSeat.seatNumber}
                    onChange={handleSelectedSeatChange}
                  >
                    <MenuItem value="">Select a seat</MenuItem>
                    {availableSeatsForSide.map((seat) => (
                      <MenuItem key={seat.seatNumber} value={seat.seatNumber}>
                        {seat.seatNumber}
                      </MenuItem>
                    ))}
                  </Select>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Seat;
