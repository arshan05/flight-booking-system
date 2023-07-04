import axios from "axios";
import { useDispatch, useSelector } from "react-redux";
import { authActions } from "../store/auth-slice";

const API_BASE_URL = "http://localhost:9098/api/auth";

export const login = (loginRequest) => {
  return async (dispatch) => {
    try {
      const response = await axios.post(
        `${API_BASE_URL}/signin`,
        loginRequest,
        { withCredentials: true }
      );
      // console.log("Response Data:", response.data);

      const cookies = document.cookie.split(";");
      let jwtToken = null;

      cookies.forEach((cookie) => {
        const cookieParts = cookie.trim().split("=");
        const cookieName = cookieParts[0];
        const cookieValue = cookieParts[1];

        if (cookieName === "login") {
          jwtToken = cookieValue;
        }
      });
      const authResponse = {
        isAuthenticated: true,
        email: loginRequest.username,
        role: response.data.roles,
        token: response.data.jwtToken,
      };
      // console.log(authResponse);
      dispatch(authActions.authenticate(authResponse));
    } catch (error) {
      console.error(error);
    }
  };
};

export const logout = () => {
  return async (dispatch) => {
    try {
      const response = await axios.get(`${API_BASE_URL}/logout`, {
        withCredentials: true,
      });

      const authResponse = {
        isAuthenticated: false,
        email: "",
        role: "",
        token: "",
      };
      // console.log(authResponse);
      dispatch(authActions.authenticate(authResponse));
    } catch (error) {
      console.error(error);
    }
  };
};

export const validate = () => {
  return async (dispatch) => {
    try {
      const response = await axios.get(
        `${API_BASE_URL}/validate`,
        { withCredentials: true }
      );
      if(response.status === 200){
        const authResponse = {
          isAuthenticated: true,
          email: response.data.name,
          role: response.data.role,
          token: response.data.jwtToken
        };
        dispatch(authActions.authenticate(authResponse));
      }
      else{
        console.log('cookie not found');
      }
    } catch (error) {
      console.log('cookie not found');
    }
  };
};
