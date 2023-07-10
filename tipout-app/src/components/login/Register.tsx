import React, { useState } from "react";
import api from "../../API/axiosConfig";
import Banner from "../Banner";
import RegisterForm from "./RegisterForm";

const Register = () => {
  const [employerRegistrationFormDTO, setEmployerRegistrationFormDTO] =
    useState({
      businessName: "",
      username: "",
      password: "",
      verifyPassword: "",
    });

  const [businessNameInput, setBusinessNameInput] = useState("");
  const [usernameInput, setUsernameInput] = useState("");
  const [passwordInput, setPasswordInput] = useState("");
  const [verifyPasswordInput, setVerifyPasswordInput] = useState("");

  // const handleChange = (e: React.ChangeEvent<any>) => {
  //   let updatedValue = {
  //     businessName: e.businessName.value,
  //     username: username.target.value,
  //     password: password.target.value,
  //     verifyPassword: verifyPassword.target.value,
  //   };

  //   setEmployerRegistrationFormDTO((employerRegistrationFormDTO) => ({
  //     ...employerRegistrationFormDTO,
  //     ...updatedValue,
  //   }));
  // };

  const addEmployer = async (e: React.ChangeEvent<any>) => {
    e.preventDefault();
    setEmployerRegistrationFormDTO({
      businessName: businessNameInput,
      username: usernameInput,
      password: passwordInput,
      verifyPassword: verifyPasswordInput,
    });

    try {
      const response = await api.post("http://localhost:8080/register", {
        employerRegistrationFormDTO: employerRegistrationFormDTO,
      });
      console.log(response.status);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <>
      <Banner />
      <RegisterForm
        handleSubmit={addEmployer}
        setBusinessName={setBusinessNameInput}
        setUsername={setUsernameInput}
        setPassword={setPasswordInput}
        setVerifyPassword={setVerifyPasswordInput}
      />
    </>
  );
};

export default Register;
