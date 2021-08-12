package com.dayue.orderservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengdayue
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @GetMapping("/findById")
    public String findById(@RequestParam("id") Long id) {
        return "订单查找,id：" + id;
    }
}
