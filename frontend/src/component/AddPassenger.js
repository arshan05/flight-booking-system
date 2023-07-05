import { TextField } from "@mui/material";
import { MuiTelInput } from "mui-tel-input";
import { useState } from "react";

const AddPassenger = () => {
  const [value, setValue] = useState("");

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
                  />
                </div>

                <div>
                  <MuiTelInput
                    margin="normal"
                    fullWidth
                    label="Contact Number"
                    defaultCountry="IN"
                    onChange={handleChange}
                    value={value}
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
