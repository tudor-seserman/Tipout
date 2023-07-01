import axios from "axios";

export default axios.create({
  url: "http://localhost:5173/",
  baseURL: "http://localhost:8080/",
  headers: {
    common: {
      Authorization: "AUTH_TOKEN_FROM_INSTANCE",
    },
  },
});
