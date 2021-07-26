package com.dayue.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengdayue
 */
@RestController
@RequestMapping("/api")
public class TestController {

    //默认引入security依赖，基本认证Basic Auth，账号为user，密码在控制台输出Using generated security password:xxx，可以在yaml文件设置好
    @GetMapping("/test")
    public String test() {
        return "hello!";
    }
}
