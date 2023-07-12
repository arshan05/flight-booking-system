import { Button, TextField } from "@mui/material";
import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import AuthService, { login } from "../service/AuthService";
import { useDispatch, useSelector } from "react-redux";

const LoginComponent =  () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const dispatch = useDispatch();
  const auth = useSelector((state) => state.auth);

  const navigate = useNavigate();

  useEffect(() => {
    console.log(auth);
    if (auth.isAuthenticated) {
      
      if (auth.role.includes('admin')) {
        navigate('/adminHome');
      } else if(auth.role.includes('user')) {
        navigate('/');
      }
    }
  }, [auth, navigate]);

  const LoginHandler = (event) => {
    event.preventDefault();
    const loginRequest = {
      username: email,
      password: password,
    };
    // dispatch(login(loginRequest));
    dispatch(login(loginRequest));

  };

  return (
    <div>
      <form onSubmit={LoginHandler}>
        <div className="d-flex flex-column justify-content-around">
          <TextField
            id="email"
            type="email"
            label="Email"
            variant="outlined"
            margin="normal"
            onChange={(event) => {
              setEmail(event.target.value);
            }}
          />

          <TextField
            id="password"
            type="password"
            label="Password"
            variant="outlined"
            margin="normal"
            onChange={(event) => {
              setPassword(event.target.value);
            }}
          />
        </div>
        <div className="d-flex flex-row justify-content-center m-2">
          <Button
            style={{ backgroundColor: "#142c54", color: "white" }}
            type="submit"
            variant="contained"
          >
            Login
          </Button>
        </div>
      </form>
    </div>
  );
};

export default LoginComponent;
