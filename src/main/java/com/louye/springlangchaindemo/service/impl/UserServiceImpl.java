package com.louye.springlangchaindemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louye.springlangchaindemo.domain.User;
import com.louye.springlangchaindemo.service.UserService;
import com.louye.springlangchaindemo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author louye
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-06-08 11:24:48
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public String login(String username, String password) {
        User user = query().eq("username", username).eq("password", password).one();
        if (user!= null) {
            return "success";
        }
        return "false";
    }

    @Override
    public String showTableDDL(String tableName) {
        Map<String, String> map = getBaseMapper().selectTableDDL(tableName);
        return map.get("Create Table");
    }


    @Override
    public Integer maxIdForTable(String tableName) {
        return getBaseMapper().maxIdForTable(tableName);
    }

}




