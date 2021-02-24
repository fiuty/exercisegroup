package com.dayue.rabbitmq.exchangemodel.topic;

import com.dayue.entity.Order;
import com.dayue.rabbitmq.RabbitMqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 主题topicExchange消息模型-消费者
 * @author zhengdayue
 */
@Slf4j
@Component
public class TopicConsumer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 监听并消费队列中的消息-topicExchange-*通配符
     */
    @RabbitListener(queues = RabbitMqConstants.TOPIC_ONE_QUEUE, containerFactory = "singleListenerContainer")
    public void consumeTopicMsgOne(Order order) {
        try {
            log.info("消息模型topicExchange-*-消费者-监听消费到消息：{} ", order);
        } catch (Exception e) {
            log.error("消息模型topicExchange-*-消费者-监听消费发生异常：", e);
        }
    }

    /**
     * 监听并消费队列中的消息-topicExchange-#通配符
     */
    @RabbitListener(queues = RabbitMqConstants.TOPIC_TWO_QUEUE, containerFactory = "singleListenerContainer")
    public void consumeTopicMsgTwo(Order order) {
        try {
            log.info("消息模型topicExchange-#-消费者-监听消费到消息：{} ", order);
        } catch (Exception e) {
            log.error("消息模型topicExchange-#-消费者-监听消费发生异常：", e);
        }
    }
}
