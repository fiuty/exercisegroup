package com.dayue.rabbitmq.exchangemodel.direct;

import com.dayue.entity.Order;
import com.dayue.rabbitmq.RabbitMqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 直连传输directExchange消息模型-生产者
 * @author zhengdayue
 */
@Slf4j
@Component
public class DirectPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息-基于DirectExchange消息模型-one
     */
    public void sendMsgDirectOne(Order order){
        //判断对象是否为Null
        if (order!=null){
            try {
                //设置交换机
                rabbitTemplate.setExchange(RabbitMqConstants.DIRECT_EXCHANGE);
                //设置路由1
                rabbitTemplate.setRoutingKey(RabbitMqConstants.DIRECT_ONE_ROUTING_KEY);
                //发送消息
                rabbitTemplate.convertAndSend(order);
                //打印日志
                log.info("消息模型DirectExchange-one-生产者-发送消息：{} ",order);
            }catch (Exception e){
                log.error("消息模型DirectExchange-one-生产者-发送消息:{},发生异常：{} ",order, e);
            }
        }
    }
    /**
     * 发送消息-基于DirectExchange消息模型-two
     */
    public void sendMsgDirectTwo(Order order){
        //判断对象是否为Null
        if (order!=null){
            try {
                //设置交换机
                rabbitTemplate.setExchange(RabbitMqConstants.DIRECT_EXCHANGE);
                //设置路由2
                rabbitTemplate.setRoutingKey(RabbitMqConstants.DIRECT_TWO_ROUTING_KEY);
                //发送消息
                rabbitTemplate.convertAndSend(order);
                //打印日志
                log.info("消息模型DirectExchange-two-生产者-发送消息：{} ",order);
            }catch (Exception e){
                log.error("消息模型DirectExchange-two-生产者-发送消息:{},发生异常：{} ",order, e);
            }
        }
    }
}
