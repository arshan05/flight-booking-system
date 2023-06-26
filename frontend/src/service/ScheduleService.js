import axios from "axios";

const API_BASE_URL = 'http://localhost:9091';
const ScheduleService = {
  getAllSchedules: () => {
    axios
      .get('http://localhost:9091/api/schedule/getAllSchedules')
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  },
};

export default ScheduleService;
