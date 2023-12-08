package com.syd.mylogin.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syd.mylogin.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
