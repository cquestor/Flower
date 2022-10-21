package com.halfsay.flower.service;

import com.halfsay.flower.pojo.Userinfo;

import java.util.List;

public interface IUserService {

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    public Userinfo ckUsername(String username);

    /**
     * 查询所有用户的信息
     *
     * @return 用户信息列表
     */
    public List<Userinfo> getWorkerList();
}
