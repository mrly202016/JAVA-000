package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.starter.domain.School;
import test.starter.domain.Student;

/**
 * Created by IntelliJ IDEA
 * User: yyzz
 * Date: 2020/11/18
 * Time: 11:31
 */
public class TestSpring {
    public static void main(String[] args) {
        //周四作业-基于xml装配bean
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //xml配置文件装配
        Student student123 = (Student) context.getBean("student123");
        System.out.println(student123);

        //注解装配
        School school= (School) context.getBean("school");
        school.listAll();
    }
}
