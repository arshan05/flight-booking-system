import axios from "axios";
import { useSelector } from "react-redux";
import { useNavigation } from "react-router-dom";
import { store } from "../store";

const API_BASE_URL = "http://localhost:9096";
const passengerId = 123456;
const paymentService = {
  makePayment: async (order, bookedSchedule) => {
    console.log(bookedSchedule);
   
    const currentState = store.getState();
    const myState = currentState.passenger;
    console.log(myState);
      try {
        const response = await axios.post(
          `${API_BASE_URL}/makePayment/${myState.id}`,
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
