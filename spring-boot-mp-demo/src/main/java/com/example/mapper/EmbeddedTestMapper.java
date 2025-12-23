package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.EmbeddedTest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmbeddedTestMapper extends BaseMapper<EmbeddedTest> {
}
