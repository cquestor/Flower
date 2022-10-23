package com.halfsay.flower.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.halfsay.flower.pojo.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {
    @Results(id = "membersMaps", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "date", column = "date"),
            @Result(property = "address", column = "address"),
            @Result(property = "favour", column = "favour"),
            @Result(property = "userid", column = "userid"),
            @Result(property = "userinfo", column = "userid", one = @One(select = "com.halfsay.flower.mapper.UserMapper.selectById")),
    })
    @Select("<script>" +
            "select * from members " +
            "<where>" +
            "<if test='name != null'>" +
            " name like CONCAT('%',#{name},'%') " +
            "</if>" +
            "</where>" +
            " order by id desc " +
            "</script>")
    public List<Member> getMembersPage(@Param("name") String name);
}
