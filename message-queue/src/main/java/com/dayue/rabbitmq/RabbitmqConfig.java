package com.dayue.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhengdayue
 */
@Slf4j
@Configuration
public class RabbitmqConfig {

    @Autowired
    private CachingConnectionFactory connectionFactory;

    //自动装配消息监听器所在的容器工厂配置类实例
    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    /**
     * 下面为单一消费者实例的配置
     */
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainer(){
        //定义消息监听器所在的容器工厂
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        //设置容器工厂所用的实例
        factory.setConnectionFactory(connectionFactory);
        //设置消息在传输中的格式，在这里采用JSON的格式进行传输
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        //设置并发消费者实例的初始数量。在这里为1个
        factory.setConcurrentConsumers(1);
        //设置并发消费者实例的最大数量。在这里为1个
        factory.setMaxConcurrentConsumers(1);
        //设置并发消费者实例中每个实例拉取的消息数量-在这里为1个
        factory.setPrefetchCount(1);
        return factory;
    }

    /**
     *下面为多个消费者实例的配置，主要是针对高并发业务场景的配置
     */
    @Bean(name = "multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerContainer(){
        //定义消息监听器所在的容器工厂
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        //设置容器工厂所用的实例
        factoryConfigurer.configure(factory,connectionFactory);
        //设置消息在传输中的格式。在这里采用JSON的格式进行传输
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        //设置消息的确认消费模式。在这里为NONE，表示不需要确认消费
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        //设置并发消费者实例的初始数量。在这里为10个
        factory.setConcurrentConsumers(10);
        //设置并发消费者实例的最大数量。在这里为15个
        factory.setMaxConcurrentConsumers(15);
        //设置并发消费者实例中每个实例拉取的消息数量。在这里为10个
        factory.setPrefetchCount(10);
        return factory;
    }

    //自定义配置RabbitMQ发送消息的操作组件RabbitTemplate
    @Bean
    public RabbitTemplate rabbitTemplate(){
        //设置“发送消息后进行确认”
        connectionFactory.setPublisherConfirms(true);
        //设置“发送消息后返回确认信息”
        connectionFactory.setPublisherReturns(true);
        //构造发送消息组件实例对象
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        //发送消息后，如果发送成功，则输出“消息发送成功”的反馈信息
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData,ack,cause));
        //发送消息后，如果发送失败，则输出“消息发送失败-消息丢失”的反馈信息
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message));
        //定义消息传输的格式为JSON字符串格式
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        //最终返回RabbitMQ的操作组件实例RabbitTemplate
        return rabbitTemplate;
    }

    //创建队列
    @Bean
    public Queue basicQueue(){
        return new Queue(RabbitMqConstants.BASIC_QUEUE,true);
    }

    //创建交换机：在这里以DirectExchange为例
    @Bean
    public DirectExchange basicExchange(){
        return new DirectExchange(RabbitMqConstants.BASIC_EXCHANGE,true,false);
    }

    //创建绑定
    @Bean
    public Binding basicBinding(){
        return BindingBuilder.bind(basicQueue()).to(basicExchange()).with(RabbitMqConstants.BASICE_ROUTING_KEY);
    }

    /**
     * 创建消息模型-fanoutExchange
     */
    //广播fanout消息模型-队列1
    @Bean
    public Queue fanoutQueueOne(){
        return new Queue(RabbitMqConstants.FANOUT_ONE_QUEUE,true);
    }

    //广播fanout消息模型-队列2
    @Bean
    public Queue fanoutQueueTwo(){
        return new Queue(RabbitMqConstants.FANOUT_TWO_QUEUE,true);
    }


    //广播fanout消息模型-创建交换机-fanoutExchange
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(RabbitMqConstants.FANOUT_EXCHANGE,true,false);
    }

    //广播fanout消息模型-创建绑定1
    @Bean
    public Binding fanoutBindingOne(){
        return BindingBuilder.bind(fanoutQueueOne()).to(fanoutExchange());
    }
    //广播fanout消息模型-创建绑定2
    @Bean
    public Binding fanoutBindingTwo(){
        return BindingBuilder.bind(fanoutQueueTwo()).to(fanoutExchange());
    }

    /**
     * 创建消息模型-directExchange
     */
    //直连传输direct消息模型-创建交换机-directExchange
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(RabbitMqConstants.DIRECT_EXCHANGE,true,false);
    }
    //直连传输direct消息模型-创建队列1
    @Bean
    public Queue directQueueOne(){
        return new Queue(RabbitMqConstants.DIRECT_ONE_QUEUE,true);
    }
    //直连传输direct消息模型-创建队列2
    @Bean
    public Queue directQueueTwo(){
        return new Queue(RabbitMqConstants.DIRECT_TWO_QUEUE,true);
    }
    //直连传输direct消息模型-创建绑定1
    @Bean
    public Binding directBindingOne(){
        return BindingBuilder.bind(directQueueOne()).to(directExchange()).with(RabbitMqConstants.DIRECT_ONE_ROUTING_KEY);
    }
    //直连传输direct消息模型-创建绑定2
    @Bean
    public Binding directBindingTwo(){
        return BindingBuilder.bind(directQueueTwo()).to(directExchange()).with(RabbitMqConstants.DIRECT_TWO_ROUTING_KEY);
    }

    /**
     * 创建消息模型-topicExchange
     */
    //主题topic消息模型-创建交换机-topicExchange
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(RabbitMqConstants.TOPIC_EXCHANGE,true,false);
    }
    //主题topic消息模型-创建队列1
    @Bean
    public Queue topicQueueOne(){
        return new Queue(RabbitMqConstants.TOPIC_ONE_QUEUE,true);
    }
    //主题topic消息模型-创建队列2
    @Bean
    public Queue topicQueueTwo(){
        return new Queue(RabbitMqConstants.TOPIC_TWO_QUEUE,true);
    }
    //主题topic消息模型-创建绑定-通配符为*的路由
    @Bean
    public Binding topicBindingOne(){
        return BindingBuilder.bind(topicQueueOne()).to(topicExchange()).with(RabbitMqConstants.TOPIC_ONE_ROUTING_KEY);
    }

    //主题topic消息模型-创建绑定-通配符为#的路由
    @Bean
    public Binding topicBindingTwo(){
        return BindingBuilder.bind(topicQueueTwo()).to(topicExchange()).with(RabbitMqConstants.TOPIC_TWO_ROUTING_KEY);
    }
}
