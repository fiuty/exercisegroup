package com.dayue.rabbitmq.deadqueue;

import com.dayue.entity.Order;
import com.dayue.rabbitmq.RabbitMqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 普通队列-生产者
 * @author zhengdayue
 */
@Slf4j
@Component
public class OrdinaryPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(Order order) {
        try {
            //设置交换机、路由键,发送消息
            rabbitTemplate.convertAndSend(RabbitMqConstants.DIRECT_EXCHANGE_DEAD_PRE, RabbitMqConstants.DIRECT_ROUTING_KEY_DEAD_PRE, order);
            log.info("普通队列-生产者,发送消息：{}", order);
        } catch (Exception e) {
            log.error("普通队列-生产者,发送消息异常,消息：{},异常：", order, e);
        }
    }
}
