import axios from "axios";
import { useSelector } from "react-redux";
import { useNavigation } from "react-router-dom";

const API_BASE_URL = "http://localhost:9096";
const passengerId = 123456;
const paymentService = {
  
  makePayment: async (order,bookedSchedule) => {
    console.log(bookedSchedule);
    try {
      const response = await axios.post(
        `${API_BASE_URL}/makePayment/${bookedSchedule.payload.passenger.id}`,
        order
      );

      const paymentUrl = response.data;
      window.location.href = paymentUrl.substring(9);
      // navigation.navigate('Payment', { redirectUrl });
      // console.log(paymentUrl);
      return paymentUrl;
    } catch (error) {
      console.log(error);
    }
  },
};

export default paymentService;
