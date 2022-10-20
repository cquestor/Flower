package com.halfsay.flower.controller;

import com.halfsay.flower.annotation.JWTIgnore;
import com.halfsay.flower.pojo.Loginuser;
import com.halfsay.flower.pojo.Result;
import com.halfsay.flower.pojo.Userinfo;
import com.halfsay.flower.service.IUserService;
import com.halfsay.flower.utils.JWTUtils;
import com.halfsay.flower.utils.MDigest5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
