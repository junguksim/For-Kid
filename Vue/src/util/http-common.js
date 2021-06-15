import axios from "axios";

export default axios.create({
  baseURL: "http://localhost/",
  headers: {
    "Access-Control-Allow-Origin": "*",
    "Content-Type": "application/json; charset = utf-8",
  },
});
