package test.starter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import test.starter.domain.Klass;
import test.starter.domain.School;
import test.starter.domain.Student;
import test.starter.prop.DomainPropertiesConfiguration;

/**
 * Created by IntelliJ IDEA
 * User: yyzz
 * Date: 2020/11/18
 * Time: 18:15
 */
@Configuration
@ComponentScan("test.starter.domain")
@EnableConfigurationProperties(DomainPropertiesConfiguration.class)
@RequiredArgsConstructor
public class TestSpringBootConfiguration {
    private final DomainPropertiesConfiguration props;

    @Bean
    public Student student(){
        Student student=new Student();
        student.setId(Integer.parseInt(props.getProps().getProperty("student.id")));
        student.setName(props.getProps().getProperty("student.name"));
        return student;
    }

    @Bean
    @ConditionalOnBean(name="student")
    @Autowired(required = false)
    public Klass klass(Student student){
        Klass klass=new Klass();
        klass.add(student);
        return klass;
    }

    @Bean
    @Autowired
    @Qualifier("school1")
    public School school(School school){
        return school;
    }

}
