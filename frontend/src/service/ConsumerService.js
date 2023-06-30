import axios from "axios";

const API_BASE_URL = "http://localhost:9091/api/consumer";
const ConsumerService = {
  getFlights: (flightRequest) => {
    fetch(`${API_BASE_URL}/getFlights`, {
      method: "get",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        // "cookie":"login=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZGFyc2hhbjEyMjNAZ21haWwuY29tIiwiaWF0IjoxNjg4MTM2MzYzLCJleHAiOjE2ODgyMjI3NjN9.evqR_H3lrupD4wLxIYCQjhgHup4pd6cGk1pw4Lo4j8-p5C3S9l4xRe6Fc_J6Jp0An3xBzvjANXClGbFolUcHZA",
      },
    }).then((response) => {
      console.log(response.headers);
    });
  },

  //   getFlights : (flightDetails) => {
  //   axios.get(`${API_BASE_URL}/getFlights`, {
  //       params: flightDetails})
  //     .then(response => {
  //       // Handle the response data
  //       console.log(response.data);
  //     })
  //     .catch(error => {
  //       // Handle the error
  //       console.error(error);
  //     });
  // }

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
