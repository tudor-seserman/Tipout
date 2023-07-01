import { useEffect, useState } from "react";
import api from "./API/axiosConfig";
import "./App.css";
import axios from "axios";

function App() {
  const [employees, setEmployees] = useState();
  axios.defaults.headers.common["Authorization"] = "AUTH_TOKEN";

  const getEmployees = async () => {
    try {
      const response = await api.get("api/employees");

      setEmployees(response.data);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getEmployees();
  }, []);

  return <>{JSON.stringify(employees)}</>;
}

export default App;
