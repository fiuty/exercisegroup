package com.dayue.datasource.controller;

import com.dayue.common.exception.BussinessException;
import com.dayue.common.pojo.BaseResponse;
import com.dayue.datasource.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/exception/test")
    public BaseResponse<String> exceptionTest(@RequestParam("num") Integer num) {
        if (num == 1) {
            throw new BussinessException("参数异常");
        }
        return BaseResponse.success("输入数字正确");
    }
}
