package com.dayue.springevent;

import com.dayue.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * Spring的事件驱动模型-消费者
 * @author zhengdayue
 */
@Component//加入Spring的IOC容器
@EnableAsync//允许异步执行
@Slf4j
public class OrderConsumer implements ApplicationListener<OrderEvent> {

    @Override
    @Async
    public void onApplicationEvent(OrderEvent event) {
        log.info("监听到订单,订单号：{}", ((Order) event.getSource()).getOrdernum());
    }
}
