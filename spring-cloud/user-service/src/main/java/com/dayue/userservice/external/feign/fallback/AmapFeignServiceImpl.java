package com.dayue.userservice.external.feign.fallback;


import com.dayue.userservice.external.feign.AmapFeignService;
import com.dayue.userservice.vo.AmapVO;
import org.springframework.stereotype.Component;

/**
 * @author zhengdayue
 */
@Component
public class AmapFeignServiceImpl implements AmapFeignService {

    @Override
    public AmapVO weatherInfo(Integer city, String key) {
        return null;
    }
}
