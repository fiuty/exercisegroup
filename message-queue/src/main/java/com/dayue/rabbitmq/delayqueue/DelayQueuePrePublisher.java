package com.dayue.rabbitmq.delayqueue;

import com.dayue.entity.Order;
import com.dayue.rabbitmq.RabbitMqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 普通队列-生产者
 * @author zhengdayue
 */
@Slf4j
@Component
public class DelayQueuePrePublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     */
    public void sendMsg(Order order) {
        try {
            //设置延迟队列交换机、延迟队列路由键,消息实体并且发送消息
            //rabbitTemplate.convertAndSend(RabbitMqConstants.DELAY_EXCHANGE_PRE, RabbitMqConstants.DELAY_ROUTING_KEY_PRE, order);
            //消息延迟时间设置为10秒,队列的x-message-ttl是设置30秒,以最小时间为准,即发送消息时的10秒
            rabbitTemplate.convertAndSend(RabbitMqConstants.DELAY_EXCHANGE_PRE, RabbitMqConstants.DELAY_ROUTING_KEY_PRE, order,message -> {
                message.getMessageProperties().setExpiration("10000");
                return message;
            });
            log.info("延迟队列消息发送成功,消息：{},发送时间：{}", order, LocalDateTime.now());
        } catch (Exception e) {
            log.error("延迟队列消息发送异常,消息：{},异常e：", order, e);
        }
    }
}
