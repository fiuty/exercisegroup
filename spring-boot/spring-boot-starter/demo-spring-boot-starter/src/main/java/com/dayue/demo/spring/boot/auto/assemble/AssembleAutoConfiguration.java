package com.dayue.demo.spring.boot.auto.assemble;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhengdayue
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(AssembleProperties.class)
//当引入rabbimq相关依赖时才自动装配
@ConditionalOnClass(name = {"org.springframework.amqp.rabbit.core.RabbitTemplate"})
//@ConditionalOnClass({RabbitTemplate.class})
@Slf4j
public class AssembleAutoConfiguration {

    @Bean
    public Assemble assemble(AssembleProperties assembleProperties) {
        Assemble assemble = new Assemble();
        assemble.setName(assembleProperties.getName());
        log.info("初始化自动装配Bean，assemble:{}", assemble);
        return assemble;
    }
}
