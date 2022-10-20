package com.halfsay.flower.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Userinfo {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;  //自增主键
    private String username;  //用户名称
    private String userpwd;  //用户密码
    @TableField(exist = false)
    private String olduserpwd;  //原始密码
    private Integer usertype;  //用户类型
    @TableField(exist = false)
    private String usertypeName;  //用户类型 0.管理员 1.店员
    private String gender;  //性别
    private String address;  //地址
    private String phone;  //电话

    public String getUsertypeName() {
        switch (this.usertype) {
            case 0:
                this.usertypeName = "管理员";
                break;
            case 1:
                this.usertypeName = "店员";
                break;
        }
        return this.usertypeName;
    }
}
