package com.dayue.rabbitmq;

import lombok.Data;
/**
 * @author zhengdayue
 */
@Data
public class RabbitMqConstants {

    public static final String BASIC_QUEUE = "mq.basic.info.queue";

    public static final String BASIC_EXCHANGE = "mq.basic.info.exchange";

    public static final String BASICE_ROUTING_KEY = "mq.basic.info.routing.key";

    //广播fanoutExchange消息模型
    public static final String FANOUT_ONE_QUEUE = "mq.fanout.one.queue";

    public static final String FANOUT_TWO_QUEUE = "mq.fanout.two.queue";

    public static final String FANOUT_EXCHANGE = "mq.fanout.exchange";

    //直连directExchange消息模型
    public static final String DIRECT_ONE_QUEUE = "mq.direct.one.queue";

    public static final String DIRECT_TWO_QUEUE = "mq.direct.two.queue";

    public static final String DIRECT_ONE_ROUTING_KEY = "mq.direct.routing.key.one";

    public static final String DIRECT_TWO_ROUTING_KEY = "mq.direct.routing.key.two";

    public static final String DIRECT_EXCHANGE = "mq.direct.exchange";

    //主题topicExchange消息模型
    public static final String TOPIC_ONE_QUEUE = "mq.topic.one.queue";

    public static final String TOPIC_TWO_QUEUE = "mq.topic.two.queue";

    public static final String TOPIC_ONE_ROUTING_KEY = "mq.topic.routing.key.*";

    public static final String TOPIC_TWO_ROUTING_KEY = "mq.topic.routing.key.#";

    public static final String TOPIC_EXCHANGE = "mq.topic.exchange";
}
