package com.louye.springlangchaindemo.controller;

import com.louye.springlangchaindemo.domain.Product;
import com.louye.springlangchaindemo.service.ProductService;
import com.louye.springlangchaindemo.service.ai.Factory;
import com.louye.springlangchaindemo.template.TableDataGeneratePrompt;
import com.louye.springlangchaindemo.tool.AssistantTools;
import com.louye.springlangchaindemo.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private AssistantTools assistantTools;

    @Resource
    private UserService userService;
    @Resource
    private Factory factory;
    @Resource
    private ProductService productService;

    @PostMapping("/test")
    public String test() {
        return "test success";
    }

    @PostMapping("login")
    public String login(String username, String password) {
        return userService.login(username, password);
    }

    @GetMapping("showTableDDL")
    public String showTableDDL(String tableName) {
        return userService.showTableDDL(tableName);
    }
    @GetMapping("generate")
    public String generate() {
//        String tableStructure = "create table  product\n" +
//                "(\n" +
//                "    id          int primary key,\n" +
//                "    name        varchar(50) comment '商品名称',\n" +
//                "    price       decimal(10, 2) comment '商品价格',\n" +
//                "    description text comment '商品描述',\n" +
//                "    creat_time  datetime comment '创建时间',\n" +
//                "    update_time datetime comment '更新时间'\n" +
//                ") auto_increment = 10000 comment '商品表';";
//
//        TableDataGeneratePrompt tableDataGeneratePrompt = new TableDataGeneratePrompt();
//        tableDataGeneratePrompt.setTableName("product");
////        tableDataGeneratePrompt.setTableStructure(tableStructure);
//        List<Product> products = factory.generateTestDataForProduct(tableDataGeneratePrompt);
//        log.info("生成测试数据成功，数据如下：{}", products);
//        productService.saveBatch(products);
//        return String.format("生成测试数据成功，数据如下：{}", products);
        return "生成测试数据成功";
    }
}
