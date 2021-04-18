package com.dayue.enable.typetwo.service;

import com.dayue.enable.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @author zhengdayue
 */
@Slf4j
@Service
public class StudentActionServerImpl implements ActionServer {

    @Bean
    public Student studentone() {
        log.info("初始化student bean");
        return new Student();
    }

    @Override
    public String action() {
        log.info("学生上课");
        return "学生上课";
    }

}
