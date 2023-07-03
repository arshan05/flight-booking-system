import { useSelector } from "react-redux";

const SchedulesResult = () => {
  const schedulesResult = useSelector(
    (state) => state.schedulesResult.schedulesResult
  );

  return (
    <div>
      {/* {schedulesResult.map(schedule => {
                <p>{schedule}</p>
            })} */}
      {console.log(console.log("schedulesResult"))}
      {console.log(schedulesResult)};
    </div>
  );
};

export default SchedulesResult;
