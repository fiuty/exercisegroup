package com.dayue.rabbitmq.exchangemodel.fanout;

import com.dayue.entity.Order;
import com.dayue.rabbitmq.RabbitMqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 广播fanoutExchange消息模型-生产者
 * @author zhengdayue
 */
@Slf4j
@Component
public class FanoutPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     * @param order
     */
    public void sendMsg(Order order){
        //判断是否为Null
        if (order!=null){
            try {
                //设置广播式交换机FanoutExchange
                rabbitTemplate.setExchange(RabbitMqConstants.FANOUT_EXCHANGE);
                //发送消息
                rabbitTemplate.convertAndSend(order);
                //打印日志
                log.info("消息模型fanoutExchange-生产者-发送消息：{} ", order);
            }catch (Exception e){
                log.error("消息模型fanoutExchange-生产者-发送消息:{},发生异常： ", order, e);
            }
        }
    }
}
