package com.halfsay.flower.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("card_userecard")
public class CardUseRecord {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String cid;
    @TableField(exist = false)
    private String mname;
    private Integer userid;
    @TableField(exist = false)
    private String username;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date usedate;
    @TableField(exist = false)
    private String startdate;
    @TableField(exist = false)
    private String enddate;
    private String memo;
}
