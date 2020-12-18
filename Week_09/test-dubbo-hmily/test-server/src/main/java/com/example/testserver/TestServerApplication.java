package com.example.testserver;

import com.example.testserver.zookeeper.EmbeddedZooKeeper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = "classpath*:/spring/*.xml")
@MapperScan("com.example.testserver.mapper")
public class TestServerApplication {

    public static void main(String[] args) throws InterruptedException {
        new EmbeddedZooKeeper(2181, false).start();
        // wait for embedded zookeeper start completely.
        Thread.sleep(1000);
        SpringApplication.run(TestServerApplication.class, args);
    }

}
