import React, { useState } from "react";
import {
  FormControlLabel,
  MenuItem,
  Radio,
  RadioGroup,
  Select,
  FormLabel,
} from "@mui/material";
import { getAvailableSeats, getSeatClass } from "../service/SeatService";

const Seat = (props) => {
  const schedule = props.schedule;
  const onSharedStateChanged = props.onSharedStateChanged;
  const sharedState = props.sharedState;
  // console.log(schedule.fare);
  let options = [];
  if (schedule.fare !== null || schedule != null) {
    options = Array.from(getSeatClass(schedule.fare));
  }
  const sides = ["WINDOW", "MIDDLE", "AISLE"];

  const dummy = getAvailableSeats(options[0], sides[0], schedule.fare);

  const [state, setState] = useState({
    selectedClass: options[0],
    selectedSide: sides[0],
    availableSeatsForSide: dummy,
    selectedSeat: dummy[0],
  });

  onSharedStateChanged((prevState) => {
    if (
      prevState.sharedClass !== state.selectedClass ||
      prevState.sharedSide !== state.selectedSide ||
      prevState.sharedSeat !== state.selectedSeat
    ) {
      return {
        sharedClass: state.selectedClass,
        sharedSide: state.selectedSide,
        sharedSeat: state.selectedSeat,
      };
    }
    return prevState;
  });

  const handleSelectedClassChange = (event) => {
    const selectedClass = event.target.value;
    const availableSeatsForSide = getAvailableSeats(
      selectedClass,
      state.selectedSide,
      schedule.fare
    );
    const selectedSeat = availableSeatsForSide[0];

    setState((prevState) => ({
      ...prevState,
      selectedClass,
      availableSeatsForSide,
      selectedSeat,
    }));

    onSharedStateChanged((prevState) => ({
      ...prevState,
      sharedClass: selectedClass,
      sharedSeat: selectedSeat,
    }));
  };

  const handleSelectedSideChange = (event) => {
    const selectedSide = event.target.value;
    const availableSeatsForSide = getAvailableSeats(
      state.selectedClass,
      selectedSide,
      schedule.fare
    );
    const selectedSeat = availableSeatsForSide[0];

    setState((prevState) => ({
      ...prevState,
      selectedSide,
      availableSeatsForSide,
      selectedSeat,
    }));

    onSharedStateChanged((prevState) => ({
      ...prevState,
      sharedSide: selectedSide,
      sharedSeat: selectedSeat,
    }));
  };

  const handleSelectedSeatChange = (event) => {
    const selectedSeatNumber = event.target.value;
    const selectedSeat = state.availableSeatsForSide.find(
      (seat) => seat.seatNumber === selectedSeatNumber
    );

    setState((prevState) => ({
      ...prevState,
      selectedSeat,
    }));

    onSharedStateChanged((prevState) => ({
      ...prevState,
      sharedSeat: selectedSeat,
    }));
  };

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
                    value={state.selectedClass}
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
                    value={state.selectedSide}
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
                    value={state.selectedSeat?.seatNumber || ""}
                    onChange={handleSelectedSeatChange}
                  >
                    <MenuItem value="">Select a seat</MenuItem>
                    {state.availableSeatsForSide.map((seat) => (
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
