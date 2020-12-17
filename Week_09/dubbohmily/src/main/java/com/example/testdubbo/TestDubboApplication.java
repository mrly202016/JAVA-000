package com.example.testdubbo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.testdubbo.dao.mapperone",sqlSessionFactoryRef = "sqlSessionFactoryOne")
@MapperScan(basePackages = "com.example.testdubbo.dao.mappertwo",sqlSessionFactoryRef = "sqlSessionFactoryTwo")
public class TestDubboApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestDubboApplication.class, args);
    }

}
