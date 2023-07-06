import axios from "axios";
import { useNavigation } from "react-router-dom";

const API_BASE_URL = "http://localhost:9096";
const passengerId = 123456;
const paymentService = {
  
  makePayment: async (order) => {
    
    try {
      const response = await axios.post(
        `${API_BASE_URL}/makePayment/${passengerId}`,
        order
      );

      const paymentUrl = response.data;
      window.location.href = paymentUrl.substring(9);
      // navigation.navigate('Payment', { redirectUrl });
      
      return paymentUrl;
    } catch (error) {
      console.log(error);
    }
  },
};

export default paymentService;
