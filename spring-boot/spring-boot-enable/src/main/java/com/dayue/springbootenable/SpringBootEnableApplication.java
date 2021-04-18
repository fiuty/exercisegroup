package com.dayue.springbootenable;

import com.dayue.enable.typetwo.annotations.EnableActionServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableActionServer
public class SpringBootEnableApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEnableApplication.class, args);
    }

}
