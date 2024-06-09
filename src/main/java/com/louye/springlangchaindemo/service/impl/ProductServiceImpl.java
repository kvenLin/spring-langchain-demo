package com.louye.springlangchaindemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louye.springlangchaindemo.domain.Product;
import com.louye.springlangchaindemo.service.ProductService;
import com.louye.springlangchaindemo.mapper.ProductMapper;
import org.springframework.stereotype.Service;

/**
* @author louye
* @description 针对表【product(商品表)】的数据库操作Service实现
* @createDate 2024-06-08 16:16:24
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

}




