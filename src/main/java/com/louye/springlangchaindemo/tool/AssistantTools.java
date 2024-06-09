package com.louye.springlangchaindemo.tool;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.louye.springlangchaindemo.domain.Product;
import com.louye.springlangchaindemo.domain.aidata.CartDataList;
import com.louye.springlangchaindemo.domain.aidata.ProductDataList;
import com.louye.springlangchaindemo.domain.aidata.UserDataList;
import com.louye.springlangchaindemo.service.CartService;
import com.louye.springlangchaindemo.service.ProductService;
import com.louye.springlangchaindemo.service.UserService;
import com.louye.springlangchaindemo.service.ai.Factory;
import com.louye.springlangchaindemo.template.TableDataGeneratePrompt;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Slf4j
@Component
public class AssistantTools {
    @Resource
    private Factory factory;
    @Resource
    private ProductService productService;
    @Resource
    private CartService cartService;
    @Resource
    private UserService userService;

    @Tool
    public String currentTime() {
        return LocalTime.now().toString();
    }

    @Tool("when user need to open the system")
    public String openSystem() {
        log.info("user need to open the system, do something here");
        return "success";
    }

    @Tool("when user need to generate test data for aim table")
    public String generateTestData(@P("tableName to generate test data") String tableName,
                                   @P("number of rows to generate") Integer num) {
        log.info("query table structure");
        String createTableDDL = userService.showTableDDL(tableName);
        if (StrUtil.isEmpty(createTableDDL)) {
            throw new RuntimeException("table not exisdt");
        }
        log.info("query table max id");
        Integer maxId = userService.maxIdForTable(tableName);
        TableDataGeneratePrompt prompt = new TableDataGeneratePrompt(tableName, num, maxId);
        if (tableName.equals("user")) {
            UserDataList userDataList = factory.generateTestDataForUser(prompt);
            log.info("userDataList: {}", userDataList);
            if (CollUtil.isNotEmpty(userDataList.getUserList())) {
                userService.saveBatch(userDataList.getUserList());
            }
            return userDataList.toString();
        } else if (tableName.equals("product")) {
            ProductDataList productDataList = factory.generateTestDataForProduct(prompt);
            log.info("productDataList: {}", productDataList);
            if (CollUtil.isNotEmpty(productDataList.getProductList())) {
                productService.saveBatch(productDataList.getProductList());
            }
            return productDataList.toString();
        }else if (tableName.equals("cart")) {
            CartDataList cartDataList = factory.generateTestDataForCart(prompt);
            log.info("cartDataList: {}", cartDataList);
            if (CollUtil.isNotEmpty(cartDataList.getCartList())) {
                cartService.saveBatch(cartDataList.getCartList());
            }
            return cartDataList.toString();
        }
        return "no handle tool for this table:" + tableName;
    }

}
