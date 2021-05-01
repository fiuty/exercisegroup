package com.dayue.springbootconditional.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhengdayue
 */
@Configuration
@Slf4j
@ConditionalOnBean
public class ConditionalMessageConfiguration {

    @ConditionalOnSystemProperty(language = "Chinese")
    @Bean("language")
    public String chineseMessage() {
        log.info("初始化language bean,使用中文：你好,世界");
        return "你好,世界";
    }

    @ConditionalOnSystemProperty(language = "English")
    @Bean("language")
    public String englishMessage() {
        log.info("初始化language bean,使用英文：hello,world");
        return "hello,world";
    }
}
