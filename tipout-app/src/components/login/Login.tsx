import React, { useState } from "react";
import Banner from "../Banner";
import { useForm, SubmitHandler } from "react-hook-form";
import { useAuth } from "../../hooks/useAuth";

type Inputs = {
  username: string;
  password: string;
};
function Login() {
  const [usernameI, setUsernameI] = useState("");
  const [passwordI, setPasswordI] = useState("");

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<Inputs>();

  const loginFormDTO = {
    username: usernameI,
    password: passwordI,
  };

  const { login } = useAuth();
  const onSubmit: SubmitHandler<Inputs> = () => login(loginFormDTO);

  return (
    <>
      <Banner />

      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="form-group">
          <label>
            Username
            <input
              {...register("username", { required: true })}
              onChange={(e) => setUsernameI(e.target.value)}
            />
            {errors.username && <span>This field is required</span>}
          </label>
        </div>
        <div className="form-group">
          <label>
            Password
            <input
              type="password"
              {...register("password", { required: true })}
              onChange={(e) => setPasswordI(e.target.value)}
            />
            {errors.password && <span>This field is required</span>}
          </label>
        </div>

        <input type="submit" className="btn btn-primary" value="Login"></input>
      </form>
    </>
  );
}

export default Login;
