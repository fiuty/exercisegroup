package com.dayue.rabbitmq.ackmodel.manual;

import com.dayue.entity.Order;
import com.dayue.rabbitmq.RabbitMqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 确认消费模式为手动确认机制-MANUAL,采用直连传输directExchange消息模型-生产者
 * @author zhengdayue
 */
@Slf4j
@Component
public class ManualAckPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     */
    public void sendMsg(Order order) {
        if (order != null) {
            try {
                rabbitTemplate.setExchange(RabbitMqConstants.MANUAL_ACKNOWLEDGE_EXCHANGE);
                rabbitTemplate.setRoutingKey(RabbitMqConstants.MANUAL_ACKNOWLEDGE_ROUTING_KEY);
                rabbitTemplate.convertAndSend(order);
                log.info("确认消费模式为手动确认机制-消息模型DirectExchange-one-生产者-发送消息：{} ", order);
            }catch (Exception e){
                log.error("确认消费模式为手动确认机制-消息模型DirectExchange-one-生产者-发送消息:{},发生异常：{} ", order, e);
            }
        }
    }
}
