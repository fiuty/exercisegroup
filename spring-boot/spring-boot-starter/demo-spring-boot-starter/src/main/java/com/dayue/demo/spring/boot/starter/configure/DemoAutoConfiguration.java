package com.dayue.demo.spring.boot.starter.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zhengdayue
 */
//自动装配,项目启动即在spring容器中注册管理相关bean
@Import(DemoConfiguration.class)
@Configuration
public class DemoAutoConfiguration {
}
