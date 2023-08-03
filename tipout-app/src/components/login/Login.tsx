import React, { useState } from "react";
import api from "../../API/axiosConfig";
import LoginForm from "./LoginForm";
import Banner from "../Banner";
import { Button } from "react-bootstrap";
import { useForm, SubmitHandler } from "react-hook-form";

type Inputs = {
  username: string;
  password: string;
};
function Login() {
  const [usernameI, setUsernameI] = useState("");
  const [passwordI, setPasswordI] = useState("");

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

      await console.log(response.headers.getUserAgent);
      localStorage.setItem("token", response.data.token);
      // if (localStorage.getItem("token")) {
      //   return redirect("/login");
      // }
    } catch (error: any) {
      if (error.response) {
        // The request was made and the server responded with a status code
        // that falls out of the range of 2xx
        alert(error.response.data.message);
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

  // console.log(watch("example")); // watch input value by passing the name of it

  return (
    /* "handleSubmit" will validate your inputs before invoking "onSubmit" */
    <form onSubmit={handleSubmit(onSubmit)}>
      {/* register your input into the hook by invoking the "register" function */}
      {/* <input defaultValue="test" {...register("example")} /> */}

      {/* include validation with required or other standard HTML validation rules */}
      <input
        {...register("username", { required: true })}
        onChange={(e) => setUsernameI(e.target.value)}
      />
      {/* errors will return when field validation fails  */}
      {errors.username && <span>This field is required</span>}
      <input
        {...register("password", { required: true })}
        onChange={(e) => setPasswordI(e.target.value)}
      />
      {/* errors will return when field validation fails  */}
      {errors.password && <span>This field is required</span>}

      <input type="submit" className="btn btn-primary" value="Login"></input>
    </form>
  );
}

export default Login;
