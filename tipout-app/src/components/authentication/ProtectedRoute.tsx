import { useNavigate } from "react-router-dom";
import { useAuth } from "../../hooks/useAuth";

interface ProtectedRouteProps {
  children: JSX.Element;
}

export const ProtectedRoute = ({ children }: ProtectedRouteProps) => {
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const parseJwt = (token: String) => {
    try {
      return JSON.parse(atob(token.split(".")[1]));
    } catch (e) {
      return null;
    }
  };
  const decodedJwt = parseJwt(user.accessToken);
  if (decodedJwt.exp * 1000 < Date.now()) {
    logout();
  }

  if (!user) {
    // user is not authenticated
    return navigate("/login");
  }
  return children;
};
