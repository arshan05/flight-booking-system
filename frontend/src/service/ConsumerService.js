import axios from "axios";

const API_BASE_URL = "http://localhost:7777/api/consumer";
const ConsumerService = {
  getFlights: async (flightDetails) => {
    try {
      const response = await axios.get(`${API_BASE_URL}/getFlights`, {
        params: flightDetails,
        withCredentials: true,
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (response.status === 200) {
        console.log(response.data);
        const cookie = document.cookie;
        console.log(cookie);
      }
      else{
        console.log(response.status);
      }
    } catch (error) {
      console.log(error);
    }

  },
};

export default ConsumerService;
