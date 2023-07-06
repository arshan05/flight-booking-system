import { Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, Slide } from "@mui/material";
import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import BookingItem from "./BookingItem";

const AllBooking = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const auth = useSelector((state) => state.auth);

  const Transition = React.forwardRef(function Transition(props, ref) {
    return <Slide direction="up" ref={ref} {...props} />;
  });


  return (
    <div>
      {!auth.isAuthenticated && (
        <Dialog
          open={!auth.isAuthenticated}
          TransitionComponent={Transition}
          keepMounted
          onClose={()=>{}}
          aria-describedby="alert-dialog-slide-description"
        >
          <DialogTitle>{"User Not Signed In"}</DialogTitle>
          <DialogContent>
            <DialogContentText id="alert-dialog-slide-description">
            Please sign in to access this feature.
            </DialogContentText>
          </DialogContent>
          <DialogActions>
            <Button onClick={() => {navigate('/')}}>Home</Button>
            <Button onClick={() => {navigate('/sign')}}>Login</Button>
          </DialogActions>
        </Dialog>
      )}

      {
        auth.isAuthenticated && (
            <BookingItem/>
        )
      }
    </div>
  );
};
export default AllBooking;
