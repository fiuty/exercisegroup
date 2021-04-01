package com.dayue.demo.spring.boot.starter.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fiuty
 */
@ConfigurationProperties(prefix = "com.demo2")
@Configuration
@Data
public class TeacherProperties {

    private String name = "defaultTeacher";

    private Integer age = 30;
}
