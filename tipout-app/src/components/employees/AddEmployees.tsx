import React, { useEffect, useState } from "react";
import Select from "react-select";
import Banner from "../Banner";
import api from "../../API/axiosConfig";
import { useAuth } from "../../hooks/useAuth";

const AddEmployees = () => {
  const { user } = useAuth();
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [employeeRole, setEmployeeRole] = useState("test");
  const [employerRoles, setEmployerRoles] = useState([]);

  const customStyles = {
    control: (base, state) => ({
      ...base,
      color: "#f9f9f9",
      background: "#1a1a1a",
      borderRadius: state.isFocused ? "3px 3px 0 0" : 3,
      borderColor: state.isFocused ? "#646cff" : "green",
      boxShadow: state.isFocused ? null : null,
      "&:hover": {
        borderColor: state.isFocused ? "red" : "blue",
      },
    }),
    menu: (base) => ({
      ...base,
      color: "#f9f9f9",
      background: "#1a1a1a",
      borderRadius: 0,
      marginTop: 0,
    }),
    menuList: (base) => ({
      ...base,
      color: "#f9f9f9",
      background: "#1a1a1a",
      padding: 0,
    }),
  };

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
    console.log("stuff");
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
      <Banner></Banner>
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
        <div className="form-group">
          <label>
            What is their role?
            <Select
              onChange={(value) => setEmployeeRole(value.value)}
              options={employerRoles.map((t) => ({ value: t, label: t }))}
              styles={customStyles}
            />
          </label>
        </div>
        <div className="form-group">
          <input type="submit"></input>
        </div>
      </form>
    </>
  );
};

export default AddEmployees;
