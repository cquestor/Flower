package com.halfsay.info.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.halfsay.info.pojo.Flowerinfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FlowerMapper extends BaseMapper<Flowerinfo> {
    @Results(id = "flowerMaps", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "imgurl", column = "imgurl"),
            @Result(property = "name", column = "name"),
            @Result(property = "target", column = "target"),
            @Result(property = "flanguage", column = "flanguage")
    })
    @Select("<script>select * from flowerinfo <where> <if test='name != null'>name like CONCAT('%',#{name},'%')</if></where>order by id desc</script>")
    public List<Flowerinfo> getFlowers(@Param("name") String name);
}
