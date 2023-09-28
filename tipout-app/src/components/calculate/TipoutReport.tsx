import React, { useEffect } from "react";
import { useLocation } from "react-router-dom";
import Banner from "../Banner";
import Table from "react-bootstrap/Table";

const TipoutReport = () => {
  const { state } = useLocation();

  console.log(state);

  return (
    <>
      <Banner />
      <br />
      <h1>Tip Distribution</h1>
      <Table striped bordered>
        <tr>
          <th>Employee</th>
          <th>Money Owed</th>
        </tr>
        {Object.entries(state).map(function ([key, val]) {
          return (
            <tr>
              <td>{key}</td>
              <td>{val}</td>
            </tr>
          );
        })}
      </Table>
    </>
  );
};

export default TipoutReport;
