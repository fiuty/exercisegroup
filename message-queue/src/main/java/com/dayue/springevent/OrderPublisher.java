package com.dayue.springevent;

import com.dayue.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author zhengdayue
 */
@Component
public class OrderPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void sendMsg() {
        Order order = new Order();
        order.setOrdernum("123456");
        OrderEvent orderEvent = new OrderEvent(order);
        //发送消息
        applicationEventPublisher.publishEvent(orderEvent);
    }
}
