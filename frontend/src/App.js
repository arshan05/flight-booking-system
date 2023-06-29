import logo from "./logo.svg";
import "./App.css";
import Payment from "./component/Payment";
import Schedule from "./component/Schedule";
import Home from "./component/Home";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import NavBar from "./component/NavBar";
import Register from "./component/Register";
import Login from "./component/Login";
import AuthForm from "./component/Sign";

function App() {
  return (
    <BrowserRouter>
      <NavBar />
      <Routes>
        <Route exact path="/" Component={Home}></Route>
        <Route exact path="/flight" Component={Schedule}></Route>
        <Route exact path="/payment" Component={Payment}></Route>
        <Route exact path="/sign" Component={AuthForm}></Route>
        <Route exact path="/register" Component={Register}></Route>
        <Route exact path="/login" Component={Login}></Route>
        
        
      </Routes>
    </BrowserRouter>
  );
}

export default App;
