package com.halfsay.flower.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.halfsay.flower.mapper.UserMapper;
import com.halfsay.flower.pojo.Userinfo;
import com.halfsay.flower.service.IUserService;
import com.halfsay.flower.utils.MDigest5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    @Override
    public int add(Userinfo userinfo) {
        userinfo.setUserpwd(MDigest5.getMD5(userinfo.getUserpwd()));
        if (ckUsername(userinfo.getUsername()) != null)
            return 0;
        else
            return userMapper.insert(userinfo);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return userMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int update(Userinfo model) {
        return userMapper.updateById(model);
    }

    @Override
    public Userinfo findById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public int modfiyPwd(Userinfo model) {
        int row = 0;
        Userinfo oldUser = findById(model.getId());
        if(oldUser != null ){
            if(MDigest5.getMD5(model.getOlduserpwd()).equals(oldUser.getUserpwd())){
                oldUser.setUserpwd(MDigest5.getMD5(model.getUserpwd()));
                row = userMapper.updateById(oldUser);
            }
        }
        return row;
    }

}
