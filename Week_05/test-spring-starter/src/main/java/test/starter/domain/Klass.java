package test.starter.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Klass {

    List<Student> students;

    public void show(){
        System.out.println("Klass students:");
        students.forEach(System.out::println);
        System.out.println("**************");
    }

    public void add(Student student){
        students=new ArrayList<>();
        students.add(student);
    }

}
