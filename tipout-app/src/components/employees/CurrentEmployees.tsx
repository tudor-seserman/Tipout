import React, { useEffect, useState } from "react";
import api from "../../API/axiosConfig";
import Banner from "../Banner";
import { useAuth } from "../../hooks/useAuth";

const CurrentEmployees = () => {
  const { user } = useAuth();
  const [employees, setEmployees] = useState({});

  useEffect(() => {
    api
      .get("/employees/current", {
        headers: {
          Authorization: "Bearer " + user.accessToken,
        },
      })
      .then((res) => {
        setEmployees(res.data);
      });
  }, []);

  return (
    <>
      <Banner />
      {Object.entries(employees).map((x, index) => {
        return <div key={index}>{x.firstName}</div>;
      })}
      {/* {Object.entries(employees).forEach((employee) => {
        Object.entries(employee).map((field, index) => {
          return <div key={index}>{field}</div>;
        });
      })} */}
    </>
  );
};

export default CurrentEmployees;
