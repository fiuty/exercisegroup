package com.dayue.rabbitmq.exchangemodel.topic;

import cn.hutool.core.util.StrUtil;
import com.dayue.entity.Order;
import com.dayue.rabbitmq.RabbitMqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 主题topicExchange消息模型-生产者
 * @author zhengdayue
 */
@Slf4j
@Component
public class TopicPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息-基于TopicExchange消息模型
     */
    public void sendMsgTopic(Order order, String routingKey){
        //判断是否为null
        if (order != null && StrUtil.isNotBlank(routingKey)) {
            try {
                //指定交换机
                rabbitTemplate.setExchange(RabbitMqConstants.TOPIC_EXCHANGE);
                //指定路由的实际取值，根据不同取值，RabbitMQ将自行进行匹配通配符，从而路由到不同的队列中
                rabbitTemplate.setRoutingKey(routingKey);
                //发送消息
                rabbitTemplate.convertAndSend(order);
                //打印日志
                log.info("消息模型TopicExchange-生产者-发送消息：{},路由：{} ", order, routingKey);
            } catch (Exception e) {
                log.error("消息模型TopicExchange-生产者-发送消息:{},发生异常：{} ", order, e);
            }
        }
    }
}
