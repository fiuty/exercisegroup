package com.dayue.springbootenable;

import com.dayue.enable.typeone.EnableHelloWorld;
import com.dayue.enable.typetwo.annotations.EnableActionServer;
import com.dayue.enable.typetwo.enums.ActionType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableHelloWorld
@EnableActionServer(serverType = ActionType.STUDENT)
public class SpringBootEnableApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEnableApplication.class, args);
    }

}
