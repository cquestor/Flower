package com.halfsay.flower.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.halfsay.flower.pojo.Card;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CardMapper extends BaseMapper<Card> {
    @Delete("DELETE FROM card WHERE mid = #{mid}")
    public Integer deleteByMid(@Param("mid") String mid);

    @Results(id = "myMaps2", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "mid", column = "mid"),
            @Result(property = "mname", column = "mname"),
            @Result(property = "ctypeid", column = "ctypeid"),
            @Result(property = "ctname", column = "ctname"),
            @Result(property = "userid", column = "userid"),
            @Result(property = "username", column = "username"),
            @Result(property = "createdate", column = "createdate"),
            @Result(property = "cstate", column = "cstate"),
            @Result(property = "startdate", column = "startdate"),
            @Result(property = "enddate", column = "enddate"),
            @Result(property = "memo", column = "memo")
    })
    @Select("<script>" +
            "SELECT c.*,m.name mname,cy.card_name ctname,u.username " +
            "FROM card c " +
            "LEFT JOIN userinfo u ON c.userid = u.id " +
            "LEFT JOIN members m ON c.mid = m.id " +
            "LEFT JOIN card_type cy ON c.ctypeid = cy.id " +
            "<where>" +
            "<if test='model.id != null'>" +
            " and c.id like CONCAT('%',#{model.id},'%') " +
            "</if>" +
            "<if test='model.userid != null'>" +
            " and c.userid = #{model.userid} " +
            "</if>" +
            "<if test='model.ctypeid != null'>" +
            " and c.ctypeid = #{model.ctypeid} " +
            "</if>" +
            "<if test='model.cstate != null'>" +
            " and c.cstate = #{model.cstate} " +
            "</if>" +
            "</where>" +
            " order by createdate desc" +
            "</script>")
    public List<Card> getPageList(@Param("model") Card model);
}
