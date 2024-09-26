package com.example.mapper;

import com.example.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select(value = "select * from user where user_id=#{userId}")
    User getUserByUserId(String userId);
}
