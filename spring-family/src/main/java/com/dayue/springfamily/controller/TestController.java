package com.dayue.springfamily.controller;

import com.dayue.springfamily.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengdayue
 * @time 2022/3/13 7:50
 */
@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/one")
    public void one() {
        testService.dateSourceOne();
    }

    @GetMapping("/two")
    public void two() {
        testService.dateSourceTwo();
    }
}
