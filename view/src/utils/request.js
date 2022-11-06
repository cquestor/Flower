import axios from "axios";

const request = axios.create({
  timeout: 1000 * 4,
  baseURL: "http://120.48.48.225:3000"
  // baseURL: "http://localhost:3000"
});

request.interceptors.request.use(
  config => {
    const token = localStorage.getItem("access_halfsay");
    if (token) {
      config.headers["access_halfsay"] = token;
    }
    return config;
  },
  err => {
    return Promise.reject(err); 
  }
);

request.interceptors.response.use(res => {
  if (res.status === 200) {
    return Promise.resolve(res.data);
  } else {
    return Promise.resolve({
      statusCode: 300,
      message: "请求出错，请检查网络连接",
      data: null
    });
  }
});

export default request;
