import { useLottie } from "lottie-react";
import animationData from "../lottie/error-not-found.json";
const NoResultsFound = () => {
  const defaultOptions = {
    loop: true,
    animationData: animationData,
  };

  const { View } = useLottie(defaultOptions);

  return (
    <div className="d-flex flex-column align-items-center justify-content-center">
      <div className="col-6 col-sm-6" >{View}</div>
      <h3 className="text-danger">No Results Found</h3>
    </div>
  );
};

export default NoResultsFound;
