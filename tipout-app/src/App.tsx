import { useEffect, useState } from "react";
import api from "./API/axiosConfig";
import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./components/Home";
import Register from "./components/login/Register";
import Login from "./components/login/Login";
import AddEmployees from "./components/employees/AddEmployees";
import { ProtectedRoute } from "./components/authentication/ProtectedRoute";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />}></Route>
      <Route path="/login" element={<Login />}></Route>
      <Route path="/register" element={<Register />}></Route>
      <Route
        path="/addEmployee/*"
        element={
          <ProtectedRoute>
            <Routes>
              <Route path="/" element={<AddEmployees />} />
            </Routes>
          </ProtectedRoute>
        }
      ></Route>
    </Routes>
  );
}

export default App;
