package com.example.testdatasource;

import homework.datasource.dao.domain.User;
import homework.datasource.dao.mapper.UserMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class TestDatasourceApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        User user=new User();
        user.setUserName("tom"+ new Random().nextInt());
        user.setPassword("123456");
        userMapper.insert(user);
        User result=userMapper.getOne(1L);
        Assert.assertEquals("kate",result.getUserName());
    }

}
