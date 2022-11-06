package com.halfsay.info.service;

import com.halfsay.info.pojo.Flowerinfo;

import java.util.List;

public interface IFlowerService {
    public List<Flowerinfo> findFlower(String name);
}
