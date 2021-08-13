package com.dayue.userservice.external.feign;

import com.dayue.userservice.vo.AmapVO;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 调用高德地图查询实时天气预报，采用原生Feign客户端调用第三方平台API
 * @author zhengdayue
 * @date: 2021-08-13
 */
public interface AmapOriginalFeignService {

    @RequestLine("GET /v3/weather/weatherInfo?city={city}&key={key}")
    @Headers("Content-Type:application/json")
    AmapVO weatherInfo(@Param("city") Integer city, @Param("key") String key);
}
