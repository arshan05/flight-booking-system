import axios from 'axios';

const API_BASE_URL = 'http://localhost:9096'; 
const paymentService = {
  createRazorpayOrder: (orderRequest) => {
    return axios.post(`${API_BASE_URL}/payment/createOrder`, orderRequest)
      .then(response => response.data)
      .catch(error => {
        throw new Error(error.response.data.message);
      });
  },
};

export default paymentService;
