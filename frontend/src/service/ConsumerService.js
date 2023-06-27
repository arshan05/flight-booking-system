import axios from "axios";

const API_BASE_URL = "http://localhost:7777/api/consumer";
const ConsumerService = {

        getFlights : (flightDetails) => {
        axios.get(`${API_BASE_URL}/getFlights`, {
            params: flightDetails
        })
          .then(response => {
            // Handle the response data
            console.log(response.data);
          })
          .catch(error => {
            // Handle the error
            console.error(error);
          });
      }


//   getFlights: (FlightDetailsRequest) => {
//     const response = fetch("http://localhost:7777/api/consumer/hello", {
//       method: "GET",
//       headers: { "Content-Type": "application/json" },
//       body: JSON.stringify(FlightDetailsRequest),
//     });

//     console.log(response);

//     // axios({
//     //     method:'get',
//     //     url:'http://localhost:7777/api/consumer/hello',
//     //     { headers: { flightDetails: JSON.stringify(flightDetails) }}
//     // }).then((response) => {
//     //     console.log(response);
//     //   })
//     //   .catch((error) => {
//     //     console.log(error);
//     //   });

//     //     axios
//     //       .post(`http://localhost:7777/api/consumer/getFlights`, {FlightDetailsRequest})

//     //       .then((response) => {
//     //         console.log(response);
//     //       })
//     //       .catch((error) => {
//     //         console.log(error);
//     //       });
//   },
};

export default ConsumerService;
