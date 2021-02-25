package com.dayue.rabbitmq.ackmodel.auto;

import com.dayue.entity.Order;
import com.dayue.rabbitmq.RabbitMqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 确认消费模式为自动确认机制-AUTO,采用直连传输directExchange消息模型-生产者
 * @author zhengdayue
 */
@Slf4j
@Component
public class AutoAckPublisher {

    //定义RabbitMQ消息操作组件RabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     */
    public void sendMsg(Order order) {
        if (order != null) {
            try {
                //设置交换机
                rabbitTemplate.setExchange(RabbitMqConstants.AUTO_ACKNOWLEDGE_EXCHANGE);
                //设置路由
                rabbitTemplate.setRoutingKey(RabbitMqConstants.AUTO_ACKNOWLEDGE_ROUTING_KEY);
                //发送消息
                rabbitTemplate.convertAndSend(order);
                log.info("确认消费模式为自动确认机制-消息模型DirectExchange-one-生产者-发送消息：{} ",order);
            }catch (Exception e){
                log.error("确认消费模式为自动确认机制-消息模型DirectExchange-one-生产者-发送消息:{},发生异常：{} ",order, e);
            }
        }
    }
}
