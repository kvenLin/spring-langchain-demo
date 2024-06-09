package com.louye.springlangchaindemo.domain.aidata;

import com.louye.springlangchaindemo.domain.Product;
import dev.langchain4j.model.output.structured.Description;
import lombok.Data;

import java.util.List;

@Data
public class ProductDataList {
    @Description("number of products, max 100")
    private Integer num;
    @Description("list of products")
    private List<Product> productList;
}
