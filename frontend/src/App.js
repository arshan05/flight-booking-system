import logo from "./logo.svg";
import "./App.css";
import Payment from "./component/Payment";
import Schedule from "./component/Schedule";
import Home from "./component/Home";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import NavBar from "./component/NavBar";

function App() {
  return (
    <BrowserRouter>
      <NavBar />
      <Routes>
        <Route exact path="/" Component={Home}></Route>
        <Route exact path="/flight" Component={Schedule}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
