package com.louye.springlangchaindemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.louye.springlangchaindemo.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author louye
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-06-08 11:24:48
* @Entity com.louye.springlangchaindemo.domain.User
*/
public interface UserMapper extends BaseMapper<User> {

    Map<String, String> selectTableDDL(@Param("tableName") String tableName);

    Integer maxIdForTable(@Param("tableName") String tableName);
}




