import React, { useState } from "react";
import api from "../../API/axiosConfig";
import Banner from "../Banner";
import { Button } from "react-bootstrap";

const Register = () => {
  const [businessNameI, setBusinessNameI] = useState("");
  const [usernameI, setUsernameI] = useState("");
  const [passwordI, setPasswordI] = useState("");
  const [verifyPasswordI, setVerifyPasswordI] = useState("");

  // class employerRegistrationFormDTO {
  //   constructor(
  //     businessName: string,
  //     username: string,
  //     password: string,
  //     verifyPassword: string
  //   ) {
  //     businessName;
  //     username;
  //     password;
  //     verifyPassword;
  //   }
  // }

  const employerRegistrationFormDTO = {
    businessName: businessNameI,
    username: usernameI,
    password: passwordI,
    verifyPassword: verifyPasswordI,
  };

  const addEmployer = async (e: React.ChangeEvent<any>) => {
    e.preventDefault();

    try {
      console.log(employerRegistrationFormDTO);
      const response = await api.post(
        "http://localhost:8080/register",
        employerRegistrationFormDTO
      );
      console.log(response.status);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <>
      <Banner />
      <form method="post">
        <div className="form-group">
          <label>
            Business Name
            <input
              className="form-control"
              name={businessNameI}
              onChange={(e) => setBusinessNameI(e.target.value)}
            />
          </label>
          {/* <p
          className="error"
          th:errors="${employerRegistrationFormDTO.businessName}"
        ></p> */}
        </div>
        <div className="form-group">
          <label>
            Username
            <input
              className="form-control"
              name={usernameI}
              onChange={(e) => setUsernameI(e.target.value)}
            />
          </label>
          {/* <p className="error" th:errors="${username}"></p> */}
        </div>
        <div className="form-group">
          <label>
            Password
            <input
              className="form-control"
              name="password"
              value={passwordI}
              onChange={(e) => setPasswordI(e.target.value)}
              type="password"
            />
          </label>
          {/* <p className="error" th:errors="${password}"></p> */}
        </div>
        <div className="form-group">
          <label>
            Verify Password
            <input
              className="form-control"
              name="verifyPassword"
              value={verifyPasswordI}
              onChange={(e) => setVerifyPasswordI(e.target.value)}
              type="password"
            />
          </label>
        </div>

        <Button
          onClick={addEmployer}
          className="btn btn-primary"
          value="Register"
        >
          Register
        </Button>
      </form>
    </>
  );
};

export default Register;
