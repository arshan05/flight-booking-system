import { useDispatch } from "react-redux";
import { logout } from "../service/AuthService";

const Logout = () => {
    const dispatch = useDispatch();
    dispatch(logout());
    return (
        <div>logged out successfully</div>
    );
}

export default Logout;