import axios from "axios";

const API_BASE_URL = "http://localhost:9098/api/auth";

const axiosInstance = axios.create({
  withCredentials: true,
});
const AuthService = {
  // login: async (loginRequest) => {
  //   const response = await axios.post(
  //     "http://localhost:9098/api/auth/signin",
  //     loginRequest,
  //     {withCredentials:true}
  //   );
  //   const jwtCookie = response.headers;

  //   console.log(jwtCookie);

  //   // return {
  //   //   username: response.data.username,
  //   //   roles: response.data.roles,
  //   //   jwtToken: jwtCookie.value,
  //   // };
  // },

  // login: async (loginRequest) => {
  //   fetch(`${API_BASE_URL}/signin`, {
  //         method: "post",
  //         headers: {
  //           Accept: "application/json",
  //           "Content-Type": "application/json",
  //         },
  //         body:JSON.stringify(loginRequest),
  //         credentials:'include'
  //       }).then((response) => {
  //         console.log(response.headers);
  //       });
  // }

  login : async (loginRequest) => {
    try {
      const response = await axios.post('http://localhost:9098/api/auth/signin', loginRequest, { withCredentials: true } );
      console.log('Response Data:', response.data);

      const cookies = document.cookie.split(';');
    let jwtToken = null;

    cookies.forEach((cookie) => {
      const cookieParts = cookie.trim().split('=');
      const cookieName = cookieParts[0];
      const cookieValue = cookieParts[1];

      if (cookieName === 'login') {
        jwtToken = cookieValue;
      }
    });

    console.log('JWT Token:', jwtToken);

      // Log the cookies
      // const cookies = response.headers['set-cookie'][0];
      // console.log('Cookies:', response.headers);
      // console.log('Cookies:', cookies);
    } catch (error) {
      console.error(error);
    }
  },

  //
};

export default AuthService;
