package homework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.testdatasource.datasource.dao.mapper")
public class TestDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestDatasourceApplication.class, args);
    }

}
