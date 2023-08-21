import React, { useState } from "react";
import api from "../../API/axiosConfig";
import Banner from "../Banner";
import { redirect } from "react-router-dom";
import { useForm, SubmitHandler } from "react-hook-form";

type Inputs = {
  businessName: String;
  username: String;
  password: String;
  verifyPassword: String;
};

const Register = () => {
  const [businessNameI, setBusinessNameI] = useState("");
  const [usernameI, setUsernameI] = useState("");
  const [passwordI, setPasswordI] = useState("");
  const [verifyPasswordI, setVerifyPasswordI] = useState("");

  const {
    register,
    watch,
    handleSubmit,
    formState: { errors },
  } = useForm<Inputs>();

  const onSubmit: SubmitHandler<Inputs> = () => addEmployer();

  const employerRegistrationFormDTO = {
    businessName: businessNameI,
    username: usernameI,
    password: passwordI,
    verifyPassword: verifyPasswordI,
  };

  const addEmployer = async () => {
    try {
      // console.log(employerRegistrationFormDTO);
      const response = await api.post(
        "http://localhost:8080/register",
        employerRegistrationFormDTO
      );
      setBusinessNameI("");
      setPasswordI("");
      setUsernameI("");
      setVerifyPasswordI("");

      await console.log(response.headers.getUserAgent);
      localStorage.setItem("token", response.data.token);
      if (localStorage.getItem("token")) {
        return redirect("/login");
      }
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

  return (
    <>
      <Banner />

      <form method="post" onSubmit={handleSubmit(onSubmit)}>
        <div className="form-group">
          <label>
            Business Name
            <input
              className="form-control"
              {...register("businessName", { required: true, minLength: 3 })}
              onChange={(e) => setBusinessNameI(e.target.value)}
            />
          </label>
          {errors.businessName && (
            <div>
              Business Name is required to be at least 3 characters long
            </div>
          )}
        </div>
        <div className="form-group">
          <label>
            Username
            <input
              className="form-control"
              {...register("username", {
                required: true,
                minLength: 3,
              })}
              onChange={(e) => setUsernameI(e.target.value)}
            />
          </label>
          {errors.username && (
            <div>Username is required to be at least 3 characters long</div>
          )}
        </div>
        <div className="form-group">
          <label>
            Password
            <input
              className="form-control"
              {...register("password", {
                required: true,
                minLength: 5,
                maxLength: 30,
              })}
              value={passwordI}
              onChange={(e) => setPasswordI(e.target.value)}
              type="password"
            />
          </label>
          {errors.password && (
            <div>
              Password is required to be between 5 and 30 characters long
            </div>
          )}
        </div>
        <div className="form-group">
          <label>
            Verify Password
            <input
              className="form-control"
              {...register("verifyPassword", {
                validate: (val: string) => {
                  if (watch("password") != val) {
                    return "Your passwords do not match";
                  }
                },
              })}
              value={verifyPasswordI}
              onChange={(e) => setVerifyPasswordI(e.target.value)}
              type="password"
            />
          </label>
          {errors.verifyPassword && <div> Your passwords do not match</div>}
        </div>
        <input
          type="submit"
          className="btn btn-primary"
          value="Register"
        ></input>
      </form>
    </>
  );
};

export default Register;
