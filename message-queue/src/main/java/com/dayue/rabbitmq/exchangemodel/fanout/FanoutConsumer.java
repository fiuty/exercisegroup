package com.dayue.rabbitmq.exchangemodel.fanout;

import com.dayue.entity.Order;
import com.dayue.rabbitmq.RabbitMqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 广播fanoutExchange消息模型-消费者
 * @author zhengdayue
 */
@Slf4j
@Component
public class FanoutConsumer {

    /**
     * 监听并消费队列中的消息-fanoutExchange-one-这是第一条队列对应的消费者
     */
    @RabbitListener(queues = RabbitMqConstants.FANOUT_ONE_QUEUE,containerFactory = "singleListenerContainer")
    public void consumeFanoutMsgOne(Order order){
        try {
            log.info("消息模型fanoutExchange-one-消费者-监听消费到消息：{} ",order);
        }catch (Exception e){
            log.error("消息模型-消费者-发生异常：",e);
        }
    }
    /**
     * 监听并消费队列中的消息-fanoutExchange-two-这是第二条队列对应的消费者
     */
    @RabbitListener(queues = RabbitMqConstants.FANOUT_TWO_QUEUE,containerFactory = "singleListenerContainer")
    public void consumeFanoutMsgTwo(Order order){
        try {
            log.info("消息模型fanoutExchange-two-消费者-监听消费到消息：{} ",order);
        }catch (Exception e){
            log.error("消息模型-消费者-发生异常：",e);
        }
    }
}
