package com.dayue.enable.typeone;

import com.dayue.enable.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhengdayue
 */
@Configuration
@Slf4j
public class HelloWorldConfiguration {

    @Bean
    public Student student() {
        log.info("初始化student");
        return new Student();
    }
}
