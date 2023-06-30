import axios from "axios";

const API_BASE_URL = "http://localhost:9098/api/auth";
const AuthService = {

login: (loginRequest) => {
  fetch(`${API_BASE_URL}/signin`, {
    method: "post",
    headers: {
    'Accept': 'application/json',
    'Content-Type': 'application/json'
    },
    credentials:'include',
    body:JSON.stringify(loginRequest)
  }).then((response) => {
      console.log(response.headers);
  })
}

//   login: async (loginRequest) => {
//     const response = await axios.post(`${API_BASE_URL}/signin`, loginRequest, { withCredentials: true });

//     if (response.status === 200) {
//       console.log("status 200");
//       // console.log(response.data);
//       // const jwtCookie = response.headers['set-cookie'];
// const cookies = document.cookie;


//       console.log('cookie: ' + cookies.toString());

//       // if(cookie.includes("login=")){
//       //   console.log("cookie: "+ cookie);
//       // }
//       // else{
//       //   console.log("cookie not found");
//       // }
//     }
//     else{
//       console.log("status not 200");
//     }

//     // try {
//     //   const response = await axios.post(`${API_BASE_URL}/signin`, loginRequest);

//     //   if (response.status === 200) {
//     //     console.log("status 200");
//     //     console.log(response.data);
//     //   }

//     // } catch (error) {
//     //   // Handle the error
//     //   console.error(error);
//     // }
  // },
};

export default AuthService;
