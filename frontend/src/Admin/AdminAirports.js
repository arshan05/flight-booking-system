import { useDispatch, useSelector } from "react-redux";
import AdminAirportItem from "./AdminAirportItem";
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
import { addAirport } from "../service/AirportService";
import { Form } from "react-bootstrap";

const AdminAirports = () => {
  const dispatch = useDispatch();
  const airports = useSelector((state) => state.airport.airports);
  const locations = useSelector((state) => state.location.locations);
  const [expanded, setExpanded] = useState(false);

  const [location, setLocation] = useState("");
  const [airportName, setAirportName] = useState("");
  const [code, setCode] = useState("");

  const handleAddAirport = (event) => {
    event.preventDefault();
    const air = {
      location: location,
      airportName: airportName,
      code: code,
    };

    dispatch(addAirport(air));
  };

  return (
    <div>
      <div className="row">
        <div className="col-4 m-2">
          <div
            className="image-container"
            style={{
              height: "100vh",
              overflow: "hidden",
              position: "sticky",
              top: 0,
            }}
          >
            <img
              className="img-fluid rounded d-block"
              src="https://cdn.dribbble.com/users/1731254/screenshots/8587036/media/4c32103b6840513e14a6a55c79fd6b6d.png?resize=1000x750&vertical=center"
              style={{
                width: "100%",
                height: "100%",
                objectFit: "cover",
              }}
              alt="My Image"
            ></img>
          </div>
        </div>
        <div className="col">
          <Card
            className="m-2"
            style={{
              color: "#F6F3F4",
              backgroundColor: "#142c54",
              position: "sticky",
              top: 0,
              zIndex: 1,
            }}
          >
            <CardHeader
              title="Add Airport"
              action={
                <IconButton
                  aria-expanded={expanded}
                  aria-label="show more"
                  onClick={() => {
                    setExpanded(!expanded);
                  }}
                >
                  {!expanded && (
                    <FontAwesomeIcon color="#F6F3F4" icon={faChevronDown} />
                  )}
                  {expanded && (
                    <FontAwesomeIcon color="#F6F3F4" icon={faChevronUp} />
                  )}
                </IconButton>
              }
              onClick={() => {
                setExpanded(!expanded);
              }}
            />
            <Collapse in={expanded} timeout="auto" unmountOnExit>
              <Form
                onSubmit={handleAddAirport}
                style={{ backgroundColor: "#F6F3F4" }}
              >
                <CardContent>
                  <TextField
                    label="Airport Name"
                    margin="normal"
                    fullWidth
                    id="airportName"
                    type="text"
                    required
                    variant="standard"
                    onChange={(event) => {
                      setAirportName(event.target.value);
                    }}
                  />

                  <TextField
                    label="Code"
                    margin="normal"
                    fullWidth
                    id="state"
                    type="text"
                    required
                    variant="standard"
                    onChange={(event) => {
                      setCode(event.target.value);
                    }}
                  />

                  <FormControl fullWidth variant="standard" margin="normal">
                    <InputLabel id="select-label">Location</InputLabel>

                    <Select
                      labelId="select-label"
                      margin="normal"
                      fullWidth
                      label="Location"
                      value={location}
                      onChange={(event) => {
                        setLocation(event.target.value);
                      }}
                    >
                      <MenuItem value="">Select a location</MenuItem>
                      {locations.map((loc) => (
                        <MenuItem key={loc.id} value={loc}>
                          <Box className="d-flex flex-column">
                            <Typography variant="subtitle1">
                              {loc.place}
                            </Typography>
                            <Typography variant="body2" color="textSecondary">
                              {loc.state}, {loc.country}
                            </Typography>
                          </Box>
                        </MenuItem>
                      ))}
                    </Select>
                  </FormControl>
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
            {airports.map((air) => (
              <Grid item sm={6} lg={4} key={air.id}>
                <AdminAirportItem airport={air} />
              </Grid>
            ))}
          </Grid>
        </div>
      </div>
    </div>
  );
};

export default AdminAirports;
