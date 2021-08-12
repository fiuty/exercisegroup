package com.dayue.userservice.controller;

import com.dayue.userservice.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengdayue
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private OrderFeign orderFeign;

    @GetMapping("/test1")
    public String test1() {
        return orderFeign.findById(12L);
    }
}
