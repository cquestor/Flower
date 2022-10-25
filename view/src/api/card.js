import request from "../utils/request";

export function findCardById(id) {
  return request({
    url: `/card/findbyid/${id}`,
    method: "POST"
  });
}

export function addCardRecord(model) {
  return request({
    url: "/usecard/add",
    method: "POST",
    data: model
  });
}
