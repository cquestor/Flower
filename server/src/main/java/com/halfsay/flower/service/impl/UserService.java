package com.halfsay.flower.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.halfsay.flower.mapper.UserMapper;
import com.halfsay.flower.pojo.Userinfo;
import com.halfsay.flower.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Userinfo ckUsername(String username) {
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<Userinfo>();
        queryWrapper.eq("username", username);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Userinfo> getWorkerList() {
        return userMapper.selectList(null);
    }
}
