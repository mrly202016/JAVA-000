package test.starter.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA
 * User: yyzz
 * Date: 2020/11/18
 * Time: 14:55
 */
@Component("school1")
public class School {
    @Autowired
    Klass class1;

    public void listAll(){
        System.out.println("Klass students:");
        class1.students.forEach(System.out::println);
    }
}
