import Login from "./Login";
import Register from "./Register";
import { useState } from "react";
import '../style/navbar.css';

const Sign = () => {
  const [isLogin, setIsLogin] = useState(true);

  const toggleForm = () => {
    setIsLogin(!isLogin);
  };
  return (
    <div className="container">
      <div className="row justify-content-center mt-5">
        <div className="col-md-8">
          <div className="card">
            <div className="card-header">
              <h5 className="card-title title-font">Escapes</h5>
              <ul className="nav nav-tabs card-header-tabs">
                <li className="nav-item">
                  <button
                    className={`nav-link ${isLogin ? "active" : ""}`}
                    onClick={() => toggleForm()}
                  >
                    Login
                  </button>
                </li>
                <li className="nav-item">
                  <button
                    className={`nav-link ${!isLogin ? "active" : ""}`}
                    onClick={() => toggleForm()}
                  >
                    Register
                  </button>
                </li>
              </ul>
            </div>
            <div className="card-body">
              {isLogin ? <Login /> : <Register />}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Sign;
