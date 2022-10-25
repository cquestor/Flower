package com.halfsay.flower.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.halfsay.flower.annotation.JWTIgnore;
import com.halfsay.flower.pojo.BatchIds;
import com.halfsay.flower.pojo.Loginuser;
import com.halfsay.flower.pojo.Result;
import com.halfsay.flower.pojo.Userinfo;
import com.halfsay.flower.service.IUserService;
import com.halfsay.flower.utils.JWTUtils;
import com.halfsay.flower.utils.MDigest5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @JWTIgnore
    @RequestMapping("/login")
    public Result login(@RequestBody Userinfo userinfo) {
        String username = userinfo.getUsername();
        String userpwd = userinfo.getUserpwd();
        Result result = new Result();
        if (username == null || "".equals(username.trim())) {
            result.setStatusCode(303);
            result.setMessage("用户名不能为空");
            return result;
        }
        if (userpwd == null || "".equals(userpwd.trim())) {
            result.setStatusCode(303);
            result.setMessage("密码不能为空");
            return result;
        }
        try {
            Userinfo resultUser = userService.ckUsername(username);
            if (resultUser != null) {
                System.out.println(MDigest5.getMD5(userpwd));
                if (MDigest5.getMD5(userpwd).equals(resultUser.getUserpwd())) {
                    String jwtToken = JWTUtils.createJwtToken(resultUser.getId().toString(), resultUser.getUsername());
                    Loginuser loginuser = new Loginuser();
                    loginuser.setUserid(resultUser.getId());
                    loginuser.setUsername(resultUser.getUsername());
                    loginuser.setUsertype(resultUser.getUsertype().toString());
                    loginuser.setJwtToken(jwtToken);
                    result.setStatusCode(200);
                    result.setMessage("登录成功");
                    result.setData(loginuser);
                } else {
                    result.setStatusCode(300);
                    result.setMessage("登录失败，密码不正确");
                }
            } else {
                result.setStatusCode(404);
                result.setMessage("登录失败，不存在此用户");
            }
        } catch (Exception e) {
            result.setStatusCode(500);
            result.setMessage("登录失败，请与管理员联系");
        }
        return result;
    }

    @RequestMapping("/getworkerlist/{pageIndex}/{pageSize}")
    public Result getPageWorkerList(@PathVariable(value = "pageIndex", required = false) Integer pageIndex, @PathVariable(value = "pageSize", required = false) Integer pageSize) {
        Result result = new Result();
        try {
            pageIndex = pageIndex == null || pageIndex < 0 ? 1 : pageIndex;
            pageSize = pageSize == null || pageSize < 1 ? 12 : pageSize;
            PageHelper.startPage(pageIndex, pageSize);
            List<Userinfo> list = userService.getWorkerList();
            PageInfo<Userinfo> pageInfo = new PageInfo<Userinfo>(list);
            if (pageInfo != null && pageInfo.getTotal() > 0) {
                result.setStatusCode(200);
                result.setMessage("success");
                result.setData(pageInfo);
            } else {
                result.setStatusCode(404);
                result.setMessage("没有查询到数据");
            }
        } catch (Exception e) {
            result.setStatusCode(500);
            result.setMessage("查询数据出错，请与管理员联系");
        }
        return result;
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Userinfo userinfo) {
        Result result = new Result();
        try {
            int rows = userService.add(userinfo);
            if (rows > 0) {
                result.setStatusCode(200);
                result.setMessage("已成功添加新的员工");
            } else {
                result.setStatusCode(300);
                result.setMessage("添加新的员工失败，该用户名已存在");
            }
        } catch (Exception ex) {
            System.out.println(ex);
            result.setStatusCode(500);
            result.setMessage("添加数据出错，请与管理员联系");
        }
        return result;
    }

    @RequestMapping("/deleteworkers")
    public Result deleteBatchIds(@RequestBody BatchIds ids) {
        Result result = new Result();
        try {
            int rows = userService.deleteBatch(ids.getIds());
            if (rows > 0) {
                result.setStatusCode(200);
                result.setMessage("已成功删除工作人员的数据");
            } else {
                result.setStatusCode(300);
                result.setMessage("删除数据出错，请与管理员联系");
            }
        } catch (Exception e) {
            result.setStatusCode(500);
            result.setMessage("删除数据出错，请与管理员联系");
        }
        return result;
    }

    @RequestMapping("/updateworker")
    public Result update(@RequestBody Userinfo model) {
        Result result = new Result();
        try {
            int rows = userService.update(model);
            if (rows > 0) {
                result.setStatusCode(200);
                result.setMessage("已成功修改员工数据");
            } else {
                result.setStatusCode(300);
                result.setMessage("修改员工数据失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode(500);
            result.setMessage("修改数据出错，请与管理员联系");
        }
        return result;
    }

    @RequestMapping("/modifypwd")
    public Result modify(@RequestBody Userinfo model) {
        Result result = new Result();
        try {
            int rows = userService.modfiyPwd(model);
            if (rows > 0) {
                result.setStatusCode(200);
                result.setMessage("已成功修改密码");
            } else {
                result.setStatusCode(300);
                result.setMessage("原密码不正确");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatusCode(500);
            result.setMessage("修改数据出错，请与管理员联系");
        }
        return result;
    }
}
