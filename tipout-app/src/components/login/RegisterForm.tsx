import React, { useState } from "react";
import { Button } from "react-bootstrap";

interface Props {
  handleSubmit: (e: React.ChangeEvent<any>) => Promise<void>;
  setBusinessName: Dispatch<SetStateAction<string>>;
  setUsername: Dispatch<SetStateAction<string>>;
  setPassword: Dispatch<SetStateAction<string>>;
  setVerifyPassword: Dispatch<SetStateAction<string>>;

  // employerRegistrationFormDTO: {
  //   businessName: string;
  //   username: string;
  //   password: string;
  //   verifyPassword: string;
  // };
  // setEmployerRegistrationFormDTO: React.Dispatch<
  //   React.SetStateAction<{
  //     businessName: string;
  //     username: string;
  //     password: string;
  //     verifyPassword: string;
  //   }>
  // >;
}

const Register = ({
  handleSubmit,
  setBusinessName,
  setPassword,
  setUsername,
  setVerifyPassword,
}: Props) => {
  return (
    <form method="post" action="/register">
      <div className="form-group">
        <label>
          Business Name
          <input
            className="form-control"
            name="businessName"
            value="businessName"
            onChange={(e) => setBusinessName(e.target.value)}
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
            name="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
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
            value={password}
            onChange={(e) => setPassword(e.target.value)}
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
            value={verifyPassword}
            onChange={(e) => setVerifyPassword(e.target.value)}
            type="password"
          />
        </label>
      </div>

      <Button
        onClick={handleSubmit(
          e,
          businessName,
          username,
          password,
          verifyPassword
        )}
        className="btn btn-primary"
        value="Register"
      >
        Register
      </Button>
    </form>
  );
};

export default Register;
