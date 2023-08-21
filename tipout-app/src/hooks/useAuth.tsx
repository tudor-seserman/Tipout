import { createContext, useContext, useMemo } from "react";
import { useNavigate } from "react-router-dom";
import { useLocalStorage } from "./useLocalStorage";

interface AuthContextProps {
  children: React.ReactNode;
}

const AuthContext = createContext({ user: {} });

export const AuthProvider = ({ children }: AuthContextProps) => {
  const [user, setUser] = useLocalStorage("user", "");
  const navigate = useNavigate();

  // call this function when you want to authenticate the user
  const login = async (data: any) => {
    localStorage.setItem("token", Response.data.accessToken);
    if (localStorage.getItem("token")) {
      return navigate("/");
    }
    setUser(data);
    navigate("/profile");
  };

  // call this function to sign out logged in user
  const logout = () => {
    setUser(null);
    navigate("/", { replace: true });
  };

  const value = useMemo(
    () => ({
      user,
      login,
      logout,
    }),
    [user]
  );
  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

export const useAuth = () => {
  return useContext(AuthContext);
};
