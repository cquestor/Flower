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

export function addCardType(model) {
  return request({
    url: "/cardtype/add",
    method: "POST",
    data: model
  });
}

export function ckCardTypeName(cardName) {
  return request({
    url: `/cardtype/ckcardname/${cardName}`,
    method: "POST"
  });
}

export function getCardTypeList() {
  return request({
    url: "/cardtype/getlist",
    method: "POST"
  });
}

export function deleteCardType(target) {
  return request({
    url: `/cardtype/del/${target}`,
    method: "POST"
  });
}

export function updateCardType(model) {
  return request({
    url: "/cardtype/update",
    method: "POST",
    data: model
  });
}

export function getCardList(pageIndex) {
  return request({
    url: `/card/getpagelist/${pageIndex}/12`,
    method: "POST",
    data: {}
  });
}

export function addCard(model) {
  return request({
    url: "/card/add",
    method: "POST",
    data: model
  });
}
