package com.dayue.springfamily.config;

import org.springframework.context.annotation.Configuration;

/**
 *
 * 通过添加spring boot actuator依赖，加上配置management.endpoints.web.exposure.include，访问
 * http://localhost:8080/actuator/beans可以查看spring帮我们注册和维护的bean集，下面自定义的bean也可以看到，
 * 由type信息可以得出该bean是通过spring的cglib动态代理生成的“EnhancerBySpringCGLIB”
 *
 * testConfiguration: {aliases: [], scope: "singleton",…}
 * aliases: []
 * dependencies: []
 * resource: "file [F:\\workplace\\exercisegroup\\spring-family\\target\\classes\\com\\dayue\\springfamily\\config\\TestConfiguration.class]"
 * scope: "singleton"
 * type: "com.dayue.springfamily.config.TestConfiguration$$EnhancerBySpringCGLIB$$1db6e4d"
 *
 * @author zhengdayue
 * @time 2022/3/13 6:30
 */
@Configuration
public class TestConfiguration {

    private String name;

}
