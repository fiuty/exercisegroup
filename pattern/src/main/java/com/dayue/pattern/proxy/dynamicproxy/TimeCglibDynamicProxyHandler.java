package com.dayue.pattern.proxy.dynamicproxy;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

/**
 * cglib的方式实现动态代理
 * @author zhengdayue
 */
public class TimeCglibDynamicProxyHandler implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Instant now = Instant.now();
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("spring cglib动态代理用时：" + Duration.between(now, Instant.now()).toMillis()+"ms");
        return result;
    }
}
