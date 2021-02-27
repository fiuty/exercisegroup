package com.dayue.rabbitmq.priorityqueue;

import com.dayue.entity.Order;
import com.dayue.rabbitmq.RabbitMqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 优先级队列-消费者
 * @author zhengdayue
 */
@Slf4j
@Component
public class PriorityConsumer {

    @RabbitListener(queues = RabbitMqConstants.PRIORITY_QUEUE, containerFactory = "singleListenerContainer")
    public void consumeMsg(Order order) {
        log.info("优先级队列监听消息：{}", order);
    }
}
