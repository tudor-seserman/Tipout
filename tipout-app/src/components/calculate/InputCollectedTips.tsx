import React, { useEffect, useState } from "react";
import api from "../../API/axiosConfig";
import Banner from "../Banner";
import { useAuth } from "../../hooks/useAuth";

const InputCollectedTips = () => {
  const { user } = useAuth();
  const [moneyHandlers, setMoneyHandlers] = useState([]);
  const [nonMoneyHandlers, setNonMoneyHandlers] = useState([]);

  useEffect(() => {
    api
      .get("/calculate/MoneyHandler", {
        headers: {
          Authorization: "Bearer " + user.accessToken,
        },
      })
      .then((res) => {
        console.log(res.data);
        setMoneyHandlers(res.data);
      });
  }, []);

  useEffect(() => {
    api
      .get("/calculate/NonMoneyHandler", {
        headers: {
          Authorization: "Bearer " + user.accessToken,
        },
      })
      .then((res) => {
        console.log(res.data);
        setNonMoneyHandlers(res.data);
      });
  }, []);

  return (
    <>
      <Banner />
      moneyHandlers
      <ul>
        {moneyHandlers.map(function (moneyHandler, index) {
          // returns Nathan, then John, then Jane
          return <li key={index}> {moneyHandler.lastName}</li>;
        })}
      </ul>
      nonMoneyHandlers
      <ul>
        {nonMoneyHandlers.map(function (nonMoneyHandler) {
          return <li key={nonMoneyHandler.id}> {nonMoneyHandler.lastName}</li>;
        })}
      </ul>
      {/* <form method="POST" action="/calculate/report">
        <div>
          <h3>Enter Tips</h3>
          <div>
            <label>
              {employeeName}
              <input
                type="number"
                //    th:field="${collectTips.moneyHandlerTipsMap[__${moneyHandler.id}__].tips}"
                //    class="form-control"
              />
            </label>
          </div>
        </div>

        <br />

        <div>
          <label>
            <h3>Add other employees to the Tippool</h3>
            <div>
              <input type="checkbox" value="0" />
              <label>{employeeName}</label>
              <br />
            </div>
          </label>
        </div>
        <div>
          <br />
          <input
            type="submit"
            value="Calculate Tips"
            className="btn btn-success"
          />
        </div>
      </form> */}
    </>
  );
};

export default InputCollectedTips;
