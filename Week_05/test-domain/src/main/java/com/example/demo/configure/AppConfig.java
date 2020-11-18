package com.example.demo.configure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test.starter.domain.Klass;
import test.starter.domain.Student;

/**
 * Created by IntelliJ IDEA
 * User: yyzz
 * Date: 2020/11/18
 * Time: 17:02
 */
@Configuration
public class AppConfig {
    @Bean
    public Student student(){
        Student student=new Student();
        student.setId(2);
        student.setName("boot");
        return student;
    }

    @Bean
//    @ConditionalOnMissingBean(name="student")
    @ConditionalOnBean(name="student")
    public Klass klass(){
        Student student=new Student();
        student.setId(3);
        student.setName("condition");
        Klass klass=new Klass();
        klass.add(student);
        return klass;
    }
}
