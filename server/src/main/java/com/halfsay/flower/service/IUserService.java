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

    /**
     * 增加员工
     *
     * @param userinfo 用户数据
     * @return 成功/否
     */
    public int add(Userinfo userinfo);

    /**
     * 批量删除
     *
     * @param ids 要删除的id
     * @return 成功/否
     */
    public int deleteBatch(String[] ids);

    /**
     * 修改用户数据
     *
     * @param model 用户数据
     * @return 成功/否
     */
    public int update(Userinfo model);
}
