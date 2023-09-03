import axios from "axios";

const https = axios.create({
  baseURL: process.env.NEXT_PUBLIC_API_URL,
});

https.interceptors.request.use((config) => {
  const token = JSON.parse(localStorage.getItem("accessToken") ?? '""');
  if (token) {
    config.headers["Authorization"] = `Bearer ${token}`;
  }

  config.headers["Content-Type"] = "application/json;charset=UTF-8";
  return config;
});

https.interceptors.response.use((response) => {
  if (response.status > 400) {
    throw new Error(response.data);
  }

  return response;
});

export { https };
