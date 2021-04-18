package com.dayue.enable.typetwo.service;

import com.dayue.enable.domain.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @author zhengdayue
 */
@Slf4j
@Service
public class TeacherActionServerImpl implements ActionServer {

    @Bean
    public Teacher teacherone() {
        log.info("初始化teacher bean");
        return new Teacher();
    }

    @Override
    public String action() {
        log.info("老师上课");
        return "老师上课";
    }
}
