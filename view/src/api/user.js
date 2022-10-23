import request from "../utils/request";

export function login(params = {}) {
  return request({
    url: "/user/login",
    method: "POST",
    data: params
  });
}

export function getWorkerList(pageIndex) {
  return request({
    url: `/user/getworkerlist/${pageIndex}/12`,
    method: "POST"
  });
}

export function addWorker(params = {}) {
  return request({
    url: "/user/add",
    method: "POST",
    data: params
  });
}

export function deleteWorkers(params = {}) {
  return request({
    url: "/user/deleteworkers",
    method: "POST",
    data: params
  });
}

export function updateWorker(model) {
  return request({
    url: "/user/updateworker",
    method: "POST",
    data: model
  });
}
