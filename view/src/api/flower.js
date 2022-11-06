import request from "../utils/request";

export function searchFlower(message) {
  return request({
    url: `/flower/${message}`,
    method: "GET"
  });
}
