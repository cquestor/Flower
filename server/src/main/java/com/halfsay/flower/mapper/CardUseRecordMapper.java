package com.halfsay.flower.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.halfsay.flower.pojo.CardUseRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CardUseRecordMapper extends BaseMapper<CardUseRecord> {
    @Results(id = "myMaps", value = {
            @Result(property = "id",column = "id"),
            @Result(property = "cid",column = "cid"),
            @Result(property = "mname",column = "mname"),
            @Result(property = "userid",column = "userid"),
            @Result(property = "username",column = "username"),
            @Result(property = "usedate",column = "usedate"),
            @Result(property = "memo",column = "memo")
    })
    @Select("<script>" +
            "SELECT cu.*,us.username,m.name mname from card_userecard cu " +
            "   INNER JOIN card c ON cu.cid = c.id" +
            "     INNER JOIN members m ON c.mid = m.id " +
            "       INNER JOIN userinfo us ON cu.userid = us.id" +
            "<where>" +
            "<if test='model.cid != null'>" +
            " and cu.cid like CONCAT('%',#{model.cid},'%') " +
            "</if>" +
            "<if test='model.userid != null'>" +
            " and cu.userid = #{model.userid} " +
            "</if>" +
            "<if test='model.startdate != null and model.enddate !=null'>" +
            " and (cu.usedate BETWEEN #{model.startdate} AND #{model.enddate})  " +
            "</if>" +
            "</where>" +
            " order by cu.usedate desc " +
            "</script>")
    public List<CardUseRecord> getPageList(@Param("model") CardUseRecord model);

    @Delete("DELETE FROM `card_userecard` WHERE cid in (SELECT id from card WHERE mid = #{mid})")
    public Integer deleteByMid(@Param("mid") String mid);
}