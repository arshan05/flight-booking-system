import React from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";
import "../style/navbar.css";
import { Link, NavLink } from "react-router-dom";

const NavBar = () => {
  return (
    <Navbar
      expand="lg"
      className="bg-body-tertiary"
    >
      <Container>
        <Link to="/" className="navbar-brand">
          <span className="title-font">Escapes</span>
        </Link>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="ms-auto d-flex justify-content-around">
            <NavLink exact to="/" className="nav-link" activeClassName="active">
              <span>home</span>
            </NavLink>

            <NavLink
              to="/payment"
              className="nav-link float-end"
              activeClassName="active"
            >
              <span>my bookings</span>
            </NavLink>

            <NavLink
              to="/sign"
              className="nav-link float-end"
              activeClassName="active"
            >
              <span>login/register</span>
            </NavLink>

          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default NavBar;
