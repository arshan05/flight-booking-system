import { useDispatch, useSelector } from "react-redux";
import AdminAirlineItem from "./AdminAirlineItem";
import {
  Button,
  Card,
  CardActions,
  CardContent,
  CardHeader,
  Collapse,
  FormLabel,
  Grid,
  IconButton,
  TextField,
  Typography,
} from "@mui/material";
import { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faChevronDown, faChevronUp } from "@fortawesome/free-solid-svg-icons";
import { addAirline } from "../service/AirlineService";
import { Form } from "react-bootstrap";

const AdminAirlines = () => {
  const dispatch = useDispatch();
  const airlines = useSelector((state) => state.airline.airlines);
  const [expanded, setExpanded] = useState(false);

  const [airlineName, setAirlineName] = useState("");
  const [code, setCode] = useState("");

  const handleAddAirline = (event) => {
    event.preventDefault();
    const air = {
      airlineName: airlineName,
      code: code,
    };
    dispatch(addAirline(air));
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
              src="https://cdn.dribbble.com/users/1037219/screenshots/7077593/media/099834f21d61c3cd391d9a81912eebd1.jpg?resize=1000x750&vertical=center"
              style={{
                width: "100%",
                height: "100%",
                objectFit: "cover",
              }}
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
              title="Add Airline"
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
                onSubmit={handleAddAirline}
                style={{ backgroundColor: "#F6F3F4" }}
              >
                <CardContent>
                  <TextField
                    label="Airline Name"
                    margin="normal"
                    fullWidth
                    id="airlineName"
                    type="text"
                    required
                    variant="standard"
                    onChange={(event) => {
                      setAirlineName(event.target.value);
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
            {airlines.map((air) => (
              <Grid item sm={6} lg={3} key={air.id}>
                <AdminAirlineItem airline={air} />
              </Grid>
            ))}
          </Grid>
        </div>
      </div>
    </div>
  );
};

export default AdminAirlines;
