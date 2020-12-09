package com.lincj.shardingdt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lincj.**.mapper")
public class ShardingJdbcDtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcDtApplication.class, args);
    }

}
