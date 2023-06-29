import axios from "axios";

const API_BASE_URL = "http://localhost:7777/api/auth";
const AuthService = {

        login : (loginRequest) => {
        axios.post(`http://localhost:7777/api/auth/signin`,loginRequest)
          .then(response => {
            // Handle the response data
            console.log(response.data);
          })
          .catch(error => {
            // Handle the error
            console.error(error);
          });
      }
};

export default AuthService;
