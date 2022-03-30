package com.dayue.ormmybatis.controller;

import com.dayue.ormmybatis.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengdayue
 * @time 2022/3/31 0:06
 */
@RestController
@RequestMapping("/api")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/test/dosomething")
    public void save() {
        teacherService.doSomething();
    }
}
