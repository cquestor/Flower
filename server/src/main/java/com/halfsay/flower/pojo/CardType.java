package com.halfsay.flower.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("card_type")
public class CardType implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("card_name")
    private String cardName;
    @TableField("card_price")
    private Double cardPrice;
    @TableField("card_expireDay")
    private Integer cardExpireDay;
    @TableField("card_see")
    private Integer cardSee;
    @TableField("card_rank")
    private Integer cardRank;
}
