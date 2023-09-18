import React, { useCallback, useEffect, useState } from "react";
import api from "../../API/axiosConfig";
import Banner from "../Banner";
import { useAuth } from "../../hooks/useAuth";

type Employee = {
  moneyHandler: {};
  nonMoneyHandler: {};
};

const InputCollectedTips = () => {
  const { user } = useAuth();
  const [moneyHandlers, setMoneyHandlers] = useState<Employee[]>([]);
  const [nonMoneyHandlers, setNonMoneyHandlers] = useState<Employee[]>([]);
  const [tipsCollected, setTipsCollected] = useState<Employee[]>([]);

  const handleMoneyHandlersChange = (event, index) => {
    let data = [...moneyHandlers];
    data[index]["tips"] = Number(event.target.value);
    setMoneyHandlers(data);
  };

  const handleNonMoneyHandlersChange = (event, index) => {
    let data = [...nonMoneyHandlers];
    data[index]["tips"] != 1
      ? (data[index]["tips"] = 1)
      : (data[index]["tips"] = null);
    setNonMoneyHandlers(data);
  };

  useEffect(() => {
    api
      .get("/calculate/MoneyHandler", {
        headers: {
          Authorization: "Bearer " + user.accessToken,
        },
      })
      .then((res) => {
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
        setNonMoneyHandlers(res.data);
      });
  }, []);

  useEffect(() => {
    setTipsCollected([...moneyHandlers, ...nonMoneyHandlers]);
  }, [moneyHandlers, nonMoneyHandlers]);

  const handleSubmit = async (e: any) => {
    e.preventDefault();
    try {
      const response = await api.post("/calculate/report", tipsCollected, {
        headers: {
          Authorization: "Bearer " + user.accessToken,
        },
      });

      console.log(response.data);
    } catch (error: any) {
      if (error.response) {
        // The request was made and the server responded with a status code
        // that falls out of the range of 2xx
        alert("Does not match user information on record.");
        console.log(error.response.data);
        console.log(error.response.status);
        console.log(error.response.headers);
      } else if (error.request) {
        // The request was made but no response was received
        // `error.request` is an instance of XMLHttpRequest in the browser
        // and an instance of http.ClientRequest in node.js
        console.log(error.request);
      } else {
        // Something happened in setting up the request that triggered an Error
        console.log("Error", error.message);
      }
    }
  };

  return (
    <>
      <Banner />
      {/* moneyHandlers
      <ul>
        {moneyHandlers.map(function (moneyHandler) {
          return <li key={moneyHandler.id}> {moneyHandler.name}</li>;
        })}
      </ul>
      nonMoneyHandlers
      <ul>
        {nonMoneyHandlers.map(function (nonMoneyHandler) {
          return <li key={nonMoneyHandler.id}> {nonMoneyHandler.name}</li>;
        })}
      </ul> */}
      <form onSubmit={handleSubmit}>
        <div>
          <h3>Enter Tips</h3>
          <div>
            {moneyHandlers.map(function (moneyHandler, index) {
              return (
                <div key={moneyHandler.id}>
                  <label>
                    {moneyHandler.name}
                    <input
                      type="number"
                      step="any"
                      placeholder="Enter Collected Tips"
                      defaultValue={moneyHandler.tips}
                      onChange={(event) =>
                        handleMoneyHandlersChange(event, index)
                      }
                      //    class="form-control"
                    />
                  </label>
                </div>
              );
            })}
          </div>
        </div>

        <br />

        <div>
          <h3>Add other employees to the Tippool</h3>

          <div>
            {nonMoneyHandlers.map(function (nonMoneyHandler, index) {
              return (
                <div key={nonMoneyHandler.id}>
                  <label>
                    {nonMoneyHandler.name}
                    <input
                      type="checkbox"
                      onChange={(event) =>
                        handleNonMoneyHandlersChange(event, index)
                      }
                    ></input>
                  </label>
                </div>
              );
            })}
          </div>
        </div>

        <div>
          <br />
          <input
            type="submit"
            value="Calculate Tips"
            className="btn btn-success"
          />
        </div>
      </form>
    </>
  );
};

export default InputCollectedTips;
