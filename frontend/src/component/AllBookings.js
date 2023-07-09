import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  Slide,
} from "@mui/material";
import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import BookingItem from "./BookingItem";
import { getAllBookings } from "../service/AllBookingsService";
import NoResultsFound from "./NoResultsFound";

const AllBooking = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const auth = useSelector((state) => state.auth);
  const allBookings = useSelector((state) => state.allBookings.bookings);


  const Transition = React.forwardRef(function Transition(props, ref) {
    return <Slide direction="up" ref={ref} {...props} />;
  });

  useEffect(() => {
    if(auth.isAuthenticated){
      dispatch(getAllBookings(auth.email));
    }
  }, [dispatch]);

  console.log(allBookings);

  return (
    <div>
      {!auth.isAuthenticated && (
        <Dialog
          open={!auth.isAuthenticated}
          TransitionComponent={Transition}
          keepMounted
          onClose={() => {}}
          aria-describedby="alert-dialog-slide-description"
        >
          <DialogTitle>{"User Not Signed In"}</DialogTitle>
          <DialogContent>
            <DialogContentText id="alert-dialog-slide-description">
              Please sign in to access this feature.
            </DialogContentText>
          </DialogContent>
          <DialogActions>
            <Button
              onClick={() => {
                navigate("/");
              }}
            >
              Home
            </Button>
            <Button
              onClick={() => {
                navigate("/sign");
              }}
            >
              Login
            </Button>
          </DialogActions>
        </Dialog>
      )}

      {auth.isAuthenticated && (
        <ul style={{ listStyleType: "none" }}>
          {
           allBookings.filter((booking) => booking !== null).map((booking) => <BookingItem booking={booking} />)

          }

          {allBookings === undefined && <NoResultsFound />}
        </ul>
      )}
    </div>
  );
};
export default AllBooking;
