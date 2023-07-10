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
        airlineName:airlineName,
        code:code
    };
    dispatch(addAirline(air));
  };

  return (
    <div>
      <Card className="m-3">
        <CardHeader
          title="Add Airline"
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
          <Form onSubmit={handleAddAirline}>
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
  );
};

export default AdminAirlines;
