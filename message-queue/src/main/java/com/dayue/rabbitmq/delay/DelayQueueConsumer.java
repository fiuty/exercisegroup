package com.dayue.rabbitmq.delay;

import com.dayue.entity.Order;
import com.dayue.rabbitmq.RabbitMqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 延迟队列-消费者
 * @author zhengdayue
 */
@Slf4j
@Component
public class DelayQueueConsumer {

    @RabbitListener(queues = RabbitMqConstants.REAL_DIRECT_QUEUE, containerFactory = "singleListenerContainerAuto")
    public void consumeMsg(Order order) {
        try {
            log.info("延迟队列-30s时间到达后,真正消费消息的队列,监听消息：{},当前时间：{}", order, LocalDateTime.now());
        } catch (Exception e) {
            log.error("延迟队列-30s时间到达后,真正消费消息的队列,监听消息：{},处理发生异常e：", order, e);
        }
    }
}
