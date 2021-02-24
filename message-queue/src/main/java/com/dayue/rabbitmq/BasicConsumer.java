package com.dayue.rabbitmq;

import cn.hutool.json.JSONUtil;
import com.dayue.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * rabbitmq demo-消费者
 * @author zhengdayue
 */
@Component
@Slf4j
public class BasicConsumer {

    /**
     * 监听并接收消费队列中的消息-在这里采用单一容器工厂实例即可
     */
    @RabbitListener(queues = RabbitMqConstants.BASIC_QUEUE, containerFactory = "singleListenerContainer")
    public void consumeMsg(Order message) {
        try {
            log.info("rabbitmq demo-消费者-监听消费到消息：{} ", JSONUtil.toJsonStr(message));
        } catch (Exception e) {
            log.error("rabbitmq demo-消费者-发生异常：", e.fillInStackTrace());
        }
    }
}
