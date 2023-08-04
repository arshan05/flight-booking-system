import { useDispatch } from "react-redux";
import { logout } from "../service/AuthService";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { useEffect } from "react";

const Logout = () => {
  const dispatch = useDispatch();

  const navigate = useNavigate();

  useEffect(() => {
    dispatch(logout());

    setTimeout(() => {
      navigate("/home");

      toast.success("Logout Successful!", {
        position: "top-right",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "light",
      });
    }, 100);
  }, [dispatch, navigate]);
};

export default Logout;
