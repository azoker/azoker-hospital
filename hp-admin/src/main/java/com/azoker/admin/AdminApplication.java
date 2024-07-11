package com.azoker.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//开启事务支持
@EnableTransactionManagement
@MapperScan(basePackages = "com.azoker.admin.mapper")
public class AdminApplication {

    public static void main(String[] args) {

        SpringApplication.run(AdminApplication.class,args);

    }

}
