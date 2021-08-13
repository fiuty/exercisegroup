package com.dayue.userservice.feign;

import com.dayue.userservice.feign.fallback.OrderFeignFallBackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhengdayue
 */
@FeignClient(value = "order-service", fallback = OrderFeignFallBackImpl.class)
public interface OrderFeign {

    @GetMapping("/api/order/findById")
    String findById(@RequestParam("id") Long id);

}
