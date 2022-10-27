import request from "../utils/request";

export function getPageMember(pageIndex) {
  return request({
    url: `/member/getpagelist/${pageIndex}/12`,
    method: "POST"
  });
}

export function addMember(model) {
  return request({
    url: "/member/add",
    method: "POST",
    data: model
  });
}

export function deleteMembers(params = {}) {
  return request({
    url: "/member/del",
    method: "POST",
    data: params
  });
}

export function findMember(pageIndex, name) {
  return request({
    url: `/member/getpagelist/${pageIndex}/12/${name}`,
    method: "POST"
  });
}

export function updateMember(model) {
  return request({
    url: "/member/update",
    method: "POST",
    data: model
  });
}

export function getAllMembers() {
  return request({
    url: "/member/getlist",
    method: "POST"
  });
}
