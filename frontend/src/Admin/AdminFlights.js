import { useDispatch, useSelector } from "react-redux";
import AdminFlightItem from "./AdminFlightItem";
import {
  Box,
  Button,
  Card,
  CardActions,
  CardContent,
  CardHeader,
  Collapse,
  FormControl,
  FormLabel,
  Grid,
  IconButton,
  InputLabel,
  MenuItem,
  Select,
  TextField,
  Typography,
} from "@mui/material";
import { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faChevronDown, faChevronUp } from "@fortawesome/free-solid-svg-icons";
import { addFlight } from "../service/FlightService";
import { Form } from "react-bootstrap";

const AdminFlights = () => {
  // flightNumber
  // airlineCompany
  // seatCapacity
  // numberOfColumns
  // seat
  // schedules
  const dispatch = useDispatch();
  const flights = useSelector((state) => state.flight.flights);
  const airlines = useSelector((state) => state.airline.airlines);
  const [expanded, setExpanded] = useState(false);

  const [flightNumber, setFlightNumber] = useState("");
  const [airlineCompany, setAirlineCompany] = useState("");
  const [seatCapacity, setSeatCapacity] = useState("");
  const [numberOfColumns, setNumberOfColumns] = useState("");
  const [seat, setSeat] = useState("");
  const [schedules, setSchedules] = useState("");

  const handleAddFlight = (event) => {
    event.preventDefault();
    const flightData = {
      flightNumber: flightNumber,
      airlineCompany: airlineCompany,
      seatCapacity: seatCapacity,
      numberOfColumns: numberOfColumns,
    };

    dispatch(addFlight(flightData));
  };

  return (
    <div>
      <Card className="m-3">
        <CardHeader
          title="Add Flight"
          style={{ color: "#142c54" }}
          action={
            <IconButton
              aria-expanded={expanded}
              aria-label="show more"
              onClick={() => {
                setExpanded(!expanded);
              }}
            >
              {!expanded && <FontAwesomeIcon icon={faChevronDown} />}
              {expanded && <FontAwesomeIcon icon={faChevronUp} />}
            </IconButton>
          }
          onClick={() => {
            setExpanded(!expanded);
          }}
        />
        <Collapse in={expanded} timeout="auto" unmountOnExit>
          <Form onSubmit={handleAddFlight}>
            <CardContent>
              <TextField
                label="Flight Number"
                margin="normal"
                fullWidth
                id="flightNumber"
                type="text"
                required
                variant="standard"
                onChange={(event) => {
                  setFlightNumber(event.target.value);
                }}
              />

              <FormControl fullWidth variant="standard" margin="normal">
                <InputLabel id="select-label">Airline Company</InputLabel>

                <Select
                  labelId="select-label"
                  margin="normal"
                  fullWidth
                  label="Airline"
                  value={airlineCompany}
                  onChange={(event) => {
                    setAirlineCompany(event.target.value);
                  }}
                >
                  <MenuItem value="">Select a airline</MenuItem>
                  {airlines.map((air) => (
                    <MenuItem key={air.id} value={air}>
                      <Box className="d-flex flex-row justify-content around align-items-center">
                        <Typography variant="subtitle1">
                          {air.airlineName} ({air.code})
                        </Typography>
                      </Box>
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>

              <TextField
                label="Seat Capacity"
                margin="normal"
                fullWidth
                id="state"
                type="text"
                required
                variant="standard"
                onChange={(event) => {
                  setSeatCapacity(event.target.value);
                }}
              />

              <TextField
                label="Number of Columns"
                margin="normal"
                fullWidth
                id="state"
                type="text"
                required
                variant="standard"
                onChange={(event) => {
                  setNumberOfColumns(event.target.value);
                }}
              />
            </CardContent>
            <CardActions className="d-flex justify-content-center">
              <Button fullWidth variant="contained" type="submit">
                Add
              </Button>
            </CardActions>
          </Form>
        </Collapse>
      </Card>

      <Grid container spacing={4}>
        {flights.map((air) => (
          <Grid item sm={6} lg={3} key={air.id}>
            <AdminFlightItem flight={air} />
          </Grid>
        ))}
      </Grid>
    </div>
  );
};

export default AdminFlights;
