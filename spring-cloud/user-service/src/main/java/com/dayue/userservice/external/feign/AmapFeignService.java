package com.dayue.userservice.external.feign;

import com.dayue.userservice.external.feign.fallback.AmapFeignServiceImpl;
import com.dayue.userservice.vo.AmapVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 调用高德地图查询实时天气预报，采用openFeign调用第三方平台API
 * @author zhengdayue
 */
@FeignClient(value = "amap", url = "https://restapi.amap.com", fallback = AmapFeignServiceImpl.class)
public interface AmapFeignService {

    @GetMapping("/v3/weather/weatherInfo")
    AmapVO weatherInfo(@RequestParam("city") Integer city, @RequestParam("key") String key);
}
