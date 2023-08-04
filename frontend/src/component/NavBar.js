import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
} from "@mui/material";
import React, { useState } from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { useDispatch, useSelector } from "react-redux";
import { Link, NavLink, useNavigate } from "react-router-dom";
import "../style/navbar.css";
import { toast } from "react-toastify";
import { logout } from "../service/AuthService";

const NavBar = () => {
  const auth = useSelector((state) => state.auth);
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [logoutConfirmationOpen, setLogoutConfirmationOpen] = useState(false);

  const handleLogout = () => {
    setLogoutConfirmationOpen(false);

    navigate("/logout");
  };

  return (
    <div>
      <Navbar expand="lg" style={{ backgroundColor: "#E7EEF9" }}>
        <Container>
          <Link to="/" className="navbar-brand">
            <span className="title-font">Escapes</span>
          </Link>

          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="ms-auto d-flex justify-content-around">
              <NavLink to="/" className="nav-link">
                <span>Home</span>
              </NavLink>

              {!auth.role.includes("admin") && (
                <NavLink to="/allBooking" className="nav-link float-end">
                  <span>My bookings</span>
                </NavLink>
              )}

              {!auth.isAuthenticated && (
                <NavLink to="/sign" className="nav-link float-end">
                  <span>Login/Register</span>
                </NavLink>
              )}

              {auth.isAuthenticated && (
                <NavLink
                  to="#"
                  className="nav-link"
                  onClick={() => setLogoutConfirmationOpen(true)}
                >
                  <span style={{color:'crimson'}}>Logout</span>
                </NavLink>
              )}
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>

      <Dialog
        className="paper"
        open={logoutConfirmationOpen}
        keepMounted
        onClose={() => setLogoutConfirmationOpen(false)}
        aria-describedby="alert-dialog-slide-description"
      >
        <DialogTitle>{"Confirm Logout"}</DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-slide-description">
            Are you sure you want to logout?
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button
            onClick={() => {
              setLogoutConfirmationOpen(false);
            }}
          >
            Cancel
          </Button>
          <Button
            onClick={() => {
              setLogoutConfirmationOpen(false);
              handleLogout();
            }}
            className="bg-danger text-white"
            autoFocus
          >
            Logout
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default NavBar;
