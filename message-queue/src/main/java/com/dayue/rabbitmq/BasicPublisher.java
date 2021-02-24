package com.dayue.rabbitmq;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.dayue.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * rabbitmq demo-生产者
 * @author zhengdayue
 */
@Component
@Slf4j
public class BasicPublisher {

    //定义RabbitMQ消息操作组件RabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**发送消息
     * @param message 待发送的消息
     */
    public void sendMsg(Order message){
        if (message != null) {
            try {
                //指定消息模型中的交换机
                rabbitTemplate.setExchange(RabbitMqConstants.BASIC_EXCHANGE);
                //指定消息模型中的路由
                rabbitTemplate.setRoutingKey(RabbitMqConstants.BASICE_ROUTING_KEY);
                //转化并发送消息
                rabbitTemplate.convertAndSend(message);
                //打印日志信息
                log.info("rabbitmq demo-生产者-发送消息：{} ", JSONUtil.toJsonStr(message));
            } catch (Exception e) {
                log.error("rabbitmq demo-生产者-发送消息发生异常：{} ", message, e.fillInStackTrace());
            }
        }
    }

}
