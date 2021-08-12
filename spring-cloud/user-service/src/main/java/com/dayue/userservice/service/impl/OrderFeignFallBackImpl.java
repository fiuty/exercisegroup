package com.dayue.userservice.service.impl;

import com.dayue.userservice.feign.OrderFeign;
import org.springframework.stereotype.Component;

/**
 * @author zhengdayue
 */
@Component
public class OrderFeignFallBackImpl implements OrderFeign {

    @Override
    public String findById(Long id) {
        return null;
    }
}
