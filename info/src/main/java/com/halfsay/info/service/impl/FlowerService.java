package com.halfsay.info.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.halfsay.info.mapper.FlowerMapper;
import com.halfsay.info.pojo.Flowerinfo;
import com.halfsay.info.service.IFlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowerService implements IFlowerService {
    private FlowerMapper flowerMapper;

    @Autowired
    public FlowerService(FlowerMapper flowerMapper) {
        this.flowerMapper = flowerMapper;
    }

    @Override
    public List<Flowerinfo> findFlower(String name) {
        return flowerMapper.getFlowers(name);
    }
}
