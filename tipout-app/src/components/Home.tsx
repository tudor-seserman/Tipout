import React from "react";
import Banner from "./Banner";
import LoginOrRegistration from "./login/LoginOrRegistration";

const Home = () => {
  return (
    <>
      <Banner />
      <LoginOrRegistration />
      <h3 className="text-center d-inline-flex p-2">
        An app to calculate end of shift distribution of tips collected and
        distributed by a restaurant employer.
      </h3>
    </>
  );
};

export default Home;
