import React, { useEffect, useState } from "react";
import Select from "react-select";
import Banner from "../Banner";
import api from "../../API/axiosConfig";
import { useAuth } from "../../hooks/useAuth";
import EmployeeRoleSelect from "./EmployeeRoleSelect";

const AddEmployees = () => {
  const { user } = useAuth();
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [employeeRole, setEmployeeRole] = useState("test");
  const [employerRoles, setEmployerRoles] = useState([]);

  useEffect(() => {
    api
      .get("/employees", {
        headers: {
          Authorization: "Bearer " + user.accessToken,
        },
      })
      .then((res) => {
        console.log(res.data);
        setEmployerRoles(res.data);
      });
  }, []);

  const CreateEmployeeDTO = {
    firstName,
    lastName,
    employeeRole,
  };

  const handleSubmit = async (e: any) => {
    e.preventDefault();
    try {
      const response = await api.post("/employees", CreateEmployeeDTO, {
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
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>
            First Name
            <input onChange={(e) => setFirstName(e.target.value)} />
          </label>
        </div>
        <div className="form-group">
          <label>
            Last Name
            <input onChange={(e) => setLastName(e.target.value)} />
          </label>
        </div>
        <EmployeeRoleSelect
          handleChange={(value) => setEmployeeRole(value.value)}
          options={employerRoles.map((t: string) => ({ value: t, label: t }))}
        />

        <div className="form-group">
          <input type="submit"></input>
        </div>
      </form>
    </>
  );
};

export default AddEmployees;
