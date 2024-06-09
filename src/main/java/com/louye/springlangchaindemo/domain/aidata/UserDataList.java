package com.louye.springlangchaindemo.domain.aidata;

import com.louye.springlangchaindemo.domain.User;
import dev.langchain4j.model.output.structured.Description;
import lombok.Data;

import java.util.List;

@Data
public class UserDataList {

    @Description("number of userList")
    private Integer num;

    @Description("list of user")
    private List<User> userList;

}
