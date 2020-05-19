package com.cxt.ssgl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.cxt.ssgl.mapper")
public class SsglApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsglApplication.class, args);
    }


}
