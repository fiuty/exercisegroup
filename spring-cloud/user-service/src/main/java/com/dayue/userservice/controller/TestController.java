package com.dayue.userservice.controller;

import com.dayue.userservice.external.feign.AmapFeignService;
import com.dayue.userservice.external.feign.service.RemoteApiService;
import com.dayue.userservice.feign.OrderFeign;
import com.dayue.userservice.vo.AmapVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengdayue
 */
@RestController
@RequestMapping("/api/test")
@Slf4j
public class TestController {

    @Autowired
    private OrderFeign orderFeign;

    @Autowired
    private AmapFeignService amapFeignService;

    @Autowired
    private RemoteApiService remoteApiService;

    @GetMapping("/test1")
    public String test1() {
        return orderFeign.findById(12L);
    }

    @GetMapping("/test2")
    public void test2() {
        //广州市
        Integer code = 440100;
        //开发者应用key
        String key = "521af2fd33f6959e0c34f5a96408ffc4";
        //采用spring cloud openFeign方式
        AmapVO amapVO = amapFeignService.weatherInfo(code, key);
        log.info("采用spring cloud openFeign方式--amapVO:{}", amapVO);
        //原生Feign方式
        AmapVO originalAmapVO = remoteApiService.amapOriginalFeignService().weatherInfo(code, key);
        log.info("原生Feign方式--originalAmapVO:{}", originalAmapVO);
    }
}
