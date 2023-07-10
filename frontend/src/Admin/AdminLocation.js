import { useDispatch, useSelector } from "react-redux";
import AdminLocationItem from "./AdminLocationItem";
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
import { addLocation } from "../service/LocationService";
import { Form } from "react-bootstrap";

const AdminLocations = () => {
  const dispatch = useDispatch();
  const locations = useSelector((state) => state.location.locations);
  const [expanded, setExpanded] = useState(false);

  const [place, setPlace] = useState("");
  const [state, setState] = useState("");
  const [country, setCountry] = useState("");

  const handleAddLocation = (event) => {
    event.preventDefault();
    const loc = {
      place: place,
      state: state,
      country: country,
    };
    console.log(loc);
    dispatch(addLocation(loc));
  };

  return (
    <div>
      <Card className="m-3">
        <CardHeader
          title="Add Location"
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
          <Form onSubmit={handleAddLocation}>
            <CardContent>
              <TextField
                label="Place"
                margin="normal"
                fullWidth
                id="place"
                type="text"
                required
                variant="standard"
                onChange={(event) => {
                  setPlace(event.target.value);
                }}
              />

              <TextField
                label="State"
                margin="normal"
                fullWidth
                id="state"
                type="text"
                required
                variant="standard"
                onChange={(event) => {
                  setState(event.target.value);
                }}
              />

              <TextField
                label="Country"
                margin="normal"
                fullWidth
                id="country"
                type="text"
                required
                variant="standard"
                onChange={(event) => {
                  setCountry(event.target.value);
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
        {locations.map((loc) => (
          <Grid item sm={6} lg={3} key={loc.id}>
            <AdminLocationItem location={loc} />
          </Grid>
        ))}
      </Grid>
    </div>
  );
};

export default AdminLocations;
