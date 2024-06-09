package com.louye.springlangchaindemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.louye.springlangchaindemo.mapper")
@SpringBootApplication
public class SpringLangchainDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringLangchainDemoApplication.class, args);
    }

}
