import { TextField } from "@mui/material";
import { MuiTelInput } from "mui-tel-input";
import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { passengerActions } from "../store/passenger-slice";

const AddPassenger = () => {
  const [value, setValue] = useState("");
  const dispatch = useDispatch();
  const passenger = useSelector((state) => state.passenger);

  const handleChange = (value) => {
    setValue(value);
  };
  return (
    <div>
      <div className="d-flex felx-column justify-content-center">
        <div className="card p-3 col-12">
          <h5 className="card-title">Passenger Details</h5>
          <div className="card-body">
            <form>
              <div className="d-flex flex-column justify-content-around">
                <div>
                  <TextField
                    margin="normal"
                    fullWidth
                    label="Passenger Name"
                    id="passangerName"
                    type="text"
                    required
                    onChange={(event) => {
                      dispatch(
                        passengerActions.setName({ name: event.target.value })
                      );
                    }}
                  />
                </div>

                <div>
                  <MuiTelInput
                    margin="normal"
                    fullWidth
                    label="Contact Number"
                    defaultCountry="IN"
                    value={value}
                    onChange={(value) => {
                      setValue(value);
                      dispatch(
                        passengerActions.setPhoneNumber({
                          phoneNumber: String(value)
                            .replace(/\D/g, "")
                            .slice(-10),
                        })
                      );
                    }}
                    required
                  />
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddPassenger;
