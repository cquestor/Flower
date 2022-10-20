package com.halfsay.flower.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.halfsay.flower.pojo.Userinfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<Userinfo> {
}
