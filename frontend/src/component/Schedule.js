import React, { useState } from "react";
import paymentService from "../service/PaymentService";

const Schedule = () => {
  return (
    <div className="card m-5 p-2 d-flex flex-row justify-content-around">
      {/* flight name & number */}
      <div>
        <div>
          <p className="fs-4 fw-bold">Air One</p>
        </div>
        <div>
          <p className="fs-6">Fl01</p>
        </div>
      </div>
      {/* start timing & place */}
      <div>
        <div>
          <p className="fs-4 fw-bold">09:15</p>
        </div>
        <div>
          <p className="fs-6">Delhi</p>
        </div>
      </div>
      {/* duration */}
      <div>
        <div>
          <p className="fs-4 fw-bolder">02 h 35 m</p>
        </div>
        <div>
          <p className="fs-6">Non stop</p>
        </div>
      </div>
      {/* end & place */}
      <div>
        <div>
          <p className="fs-4 fw-bold">11:50</p>
        </div>
        <div>
          <p className="fs-6">Bengaluru</p>
        </div>
      </div>

      {/* price */}
      <div>
        <div>
          <p className="fs-4 fw-bold">&#8377; 6000</p>
        </div>
        <div>
          <p className="fs-6">per person</p>
        </div>
      </div>
    </div>
  );
};

export default Schedule;
