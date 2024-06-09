package com.louye.springlangchaindemo.domain.aidata;

import com.louye.springlangchaindemo.domain.Cart;
import dev.langchain4j.model.output.structured.Description;
import lombok.Data;

import java.util.List;

@Data
public class CartDataList {

    @Description("number of cartList")
    private Integer num;

    @Description("list of cart")
    private List<Cart> cartList;

}
