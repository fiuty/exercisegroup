package com.dayue.pattern.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

/**
 * 动态代理,计算方法所花时间
 * @author zhengdayue
 */
public class TimeDynamicProxyHandler implements InvocationHandler {

    private Object targetObject;

    public TimeDynamicProxyHandler(Object targetObject) {
        this.targetObject = targetObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Instant now = Instant.now();
        Object result = method.invoke(targetObject, args);
        System.out.println("动态代理用时：" + Duration.between(now, Instant.now()).toMillis()+"ms");
        return result;
    }
}
