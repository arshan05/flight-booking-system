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
        <Route path="/" Component={Home}></Route>
        <Route  path="/flight" Component={Schedule}></Route>
        <Route path="/payment" Component={Payment}></Route>
        <Route path="/sign" Component={AuthForm}></Route>
        <Route path="/register" Component={Register}></Route>
        <Route path="/login" Component={Login}></Route>
        
        
      </Routes>
    </BrowserRouter>
  );
}

export default App;
