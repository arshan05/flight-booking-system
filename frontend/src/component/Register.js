import { useState } from "react";
import { Link } from "react-router-dom";
import { Button, TextField } from "@mui/material";
import { registerUser } from "../service/AuthService";

const RegisterComponent = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const RegisterHandler = (event) => {
    event.preventDefault();
    const registerRequest = {
      username: email,
      password: password,
      role: "user",
    };
// console.log(registerRequest);
    registerUser(registerRequest);
  };

  return (
    <div>
      <form onSubmit={RegisterHandler}>
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
            Register
          </Button>
        </div>
      </form>
    </div>
  );
};


// const Register =()=>{
//   return (
//     <div>signup</div>
//   )
// }
export default RegisterComponent;
