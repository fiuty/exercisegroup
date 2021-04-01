package com.dayue.project.controller;

import com.dayue.project.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fiuty
 */
@RestController
@RequestMapping("/api")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/student/info")
    public String studentInfo() {
        return demoService.studentInfo();
    }

    @GetMapping("/teacher/info")
    public String info() {
        return demoService.teacherInfo();
    }
}
