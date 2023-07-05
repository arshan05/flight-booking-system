import { TextField } from "@mui/material";
import { getSeatClass } from "../service/SeatService";

const Seat = () => {
  
    return (
        <div>
      <div className="d-flex felx-column justify-content-center">
        <div className="card p-3 col-12">
          <h5 className="card-title">Seat</h5>
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

                
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>

    );
}

export default Seat;