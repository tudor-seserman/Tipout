import React, { useState } from "react";
import api from "../../API/axiosConfig";
import LoginForm from "./LoginForm";
import Banner from "../Banner";
import { Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { useForm, SubmitHandler } from "react-hook-form";

type Inputs = {
  username: string;
  password: string;
};
function Login() {
  const [usernameI, setUsernameI] = useState("");
  const [passwordI, setPasswordI] = useState("");
  let navigate = useNavigate();

  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm<Inputs>();

  const onSubmit: SubmitHandler<Inputs> = () => login();

  const loginFormDTO = {
    username: usernameI,
    password: passwordI,
  };

  const login = async () => {
    try {
      console.log(loginFormDTO);
      const response = await api.post("auth/login", loginFormDTO, {
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
      });

      setPasswordI("");
      setUsernameI("");

      localStorage.setItem("token", response.data.accessToken);
      if (localStorage.getItem("token")) {
        return navigate("/");
      }
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

      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="form-group">
          <label>
            Username
            <input
              {...register("username", { required: true })}
              onChange={(e) => setUsernameI(e.target.value)}
            />
            {errors.username && <span>This field is required</span>}
          </label>
        </div>
        <div className="form-group">
          <label>
            Password
            <input
              type="password"
              {...register("password", { required: true })}
              onChange={(e) => setPasswordI(e.target.value)}
            />
            {errors.password && <span>This field is required</span>}
          </label>
        </div>

        <input type="submit" className="btn btn-primary" value="Login"></input>
      </form>
    </>
  );
}

export default Login;
