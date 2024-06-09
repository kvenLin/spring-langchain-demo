package com.louye.springlangchaindemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louye.springlangchaindemo.domain.Cart;
import com.louye.springlangchaindemo.service.CartService;
import com.louye.springlangchaindemo.mapper.CartMapper;
import org.springframework.stereotype.Service;

/**
* @author louye
* @description 针对表【cart(购物车表)】的数据库操作Service实现
* @createDate 2024-06-08 16:16:32
*/
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart>
    implements CartService{

}




