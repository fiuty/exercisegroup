package com.dayue.rabbitmq.deadqueue;

import com.dayue.entity.Order;
import com.dayue.rabbitmq.RabbitMqConstants;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zhengdayue
 */
@Slf4j
@Component
public class DeadQueueConsumer {

    @Autowired
    private OrdinaryPublisher ordinaryPublisher;

    //为方便演示,写死在这里,实际可以用配置中心apollo或者阿里云naocs动态刷新该值,即修复bug之后刷新该值为true
    private Boolean dynamicRepairSign = false;

    //可以注释掉监听,在rabbitmq管理后台取出该消息,等到异常处理完之后把该消息丢回原先的队列进行处理。
    //@RabbitListener(queues = RabbitMqConstants.DEAD_QUEUE, containerFactory = "singleListenerContainerManual")
    public void consumeMsg(Order order, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long tag) throws IOException {
        log.info("死信队列监听到消息：{}", order);
        if (dynamicRepairSign) {
            //修复完异常之后发送消息到原先队列进行消费
            ordinaryPublisher.sendMsg(order);
            channel.basicAck(tag, false);
        } else {
            channel.basicReject(tag, true);
        }
    }
}
