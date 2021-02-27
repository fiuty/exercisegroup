package com.dayue.rabbitmq.priorityqueue;

import com.dayue.entity.Order;
import com.dayue.rabbitmq.RabbitMqConstants;
import com.dayue.rabbitmq.RabbitmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 优先级队列-生产者
 * @author zhengdayue
 */
@Slf4j
@Component
public class PriorityPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     * @param order 订单消息
     * @param priority 优先级0-10
     */
    public void sendMsg(Order order, Integer priority) {
        rabbitTemplate.convertAndSend(RabbitMqConstants.PRIORITY_EXCHANGE, RabbitMqConstants.PRIORITY_ROUTING_KEY, order, message -> {
            message.getMessageProperties().setPriority(priority);
            return message;
        });
        log.info("优先级队列发送消息：{},优先级为：{}", order, priority);
    }

}
