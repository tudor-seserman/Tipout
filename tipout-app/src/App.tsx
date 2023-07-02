import { useEffect, useState } from "react";
import api from "./API/axiosConfig";
import "./App.css";

function App() {
  const [employees, setEmployees] = useState();

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
