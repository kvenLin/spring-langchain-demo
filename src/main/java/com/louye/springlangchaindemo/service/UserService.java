package com.louye.springlangchaindemo.service;

import com.louye.springlangchaindemo.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author louye
* @description 针对表【user】的数据库操作Service
* @createDate 2024-06-08 11:24:48
*/
public interface UserService extends IService<User> {

    String login(String username, String password);

    String showTableDDL(String tableName);

    Integer maxIdForTable(String tableName);
}
