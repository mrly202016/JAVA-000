package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import test.starter.domain.Klass;
import test.starter.domain.School;
import test.starter.domain.Student;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        //周四作业-基于springboot装配bean
        ConfigurableApplicationContext context=SpringApplication.run(DemoApplication.class,args);
        //基于@Configuration和Bean装配
        Student student= (Student) context.getBean("student");
        System.out.println(student);
        //基于@Conditional装配
        Klass klass= (Klass) context.getBean("klass");
        klass.show();

        //周六作业-starter
        Student student1= (Student) context.getBean("student");
        System.out.println(student);
        Klass klass1= (Klass) context.getBean("klass");
        klass1.show();
        School school= (School) context.getBean("school");
        school.listAll();
    }

}
