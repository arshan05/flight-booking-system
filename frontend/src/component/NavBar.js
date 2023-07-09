import React from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";
import "../style/navbar.css";
import { Link, NavLink } from "react-router-dom";
import { useSelector } from "react-redux";

const NavBar = () => {
  const auth = useSelector((state) => state.auth);
  return (
    <Navbar expand="lg" className="bg-body-tertiary">
      <Container>
        <Link to="/" className="navbar-brand">
          <span className="title-font">Escapes</span>
        </Link>

        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="ms-auto d-flex justify-content-around">
              <NavLink to="/" className="nav-link">
                <span>home</span>
              </NavLink>

            {!auth.role.includes("admin") && (
              <NavLink to="/allBooking" className="nav-link float-end">
                <span>my bookings</span>
              </NavLink>
            )}

            {!auth.isAuthenticated && (
              <NavLink to="/sign" className="nav-link float-end">
                <span>login/register</span>
              </NavLink>
            )}

            {auth.isAuthenticated && (
              <NavLink to="/logout" className="nav-link float-end">
                <span>logout</span>
              </NavLink>
            )}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default NavBar;
