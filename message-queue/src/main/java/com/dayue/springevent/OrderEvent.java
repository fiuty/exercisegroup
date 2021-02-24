package com.dayue.springevent;

import com.dayue.entity.Order;
import org.springframework.context.ApplicationEvent;

/**
 * 订单事件
 * @author zhengdayue
 */
public class OrderEvent extends ApplicationEvent {

    public OrderEvent(Order source) {
        super(source);
    }
}
