package com.halfsay.flower.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("card")
public class Card implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    private String id;
    private Integer mid;
    @TableField(exist = false)
    private String mname;
    private Integer ctypeid;
    @TableField(exist = false)
    private String ctname;
    private Integer userid;
    @TableField(exist = false)
    private String username;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createddate;
    private Integer cstate;
    @TableField(exist = false)
    private String cstateV;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date startdate;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date enddate;
    private String memo;

    public String getCstateV() {
        if (this.cstate != null)
            if (this.cstate == 0) {
                return "未使用";
            } else if (this.cstate == 1) {
                return "正常";
            } else if (this.cstate == 2) {
                return "过期";
            } else {
                return "禁用";
            }
        else return "";
    }
}
