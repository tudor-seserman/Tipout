import { useEffect, useState } from "react";
import api from "./API/axiosConfig";
import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./components/Home";
import Register from "./components/login/Register";
import Login from "./components/login/Login";

function App() {
  // const [employees, setEmployees] = useState();

  // const getEmployees = async () => {
  //   try {
  //     const response = await api.get("api/employees");

  //     setEmployees(response.data);
  //   } catch (err) {
  //     console.log(err);
  //   }
  // };

  // useEffect(() => {
  //   getEmployees();
  // }, []);

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/register" element={<Register />}></Route>
        <Route path="testing" element={<div>Route</div>}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
