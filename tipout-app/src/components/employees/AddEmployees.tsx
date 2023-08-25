import React from "react";
import Banner from "../Banner";
import api from "../../API/axiosConfig";
import { useAuth } from "../../hooks/useAuth";

const AddEmployees = () => {
  const { user } = useAuth();

  const handleSubmit = async (e: any) => {
    e.preventDefault();
    console.log("stuff");
    try {
      const response = await api.get("/employees", {
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
        <input type="submit"></input>
      </form>
    </>
  );
};

export default AddEmployees;
