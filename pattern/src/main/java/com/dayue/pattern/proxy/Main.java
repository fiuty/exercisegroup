package com.dayue.pattern.proxy;

import com.dayue.pattern.proxy.dynamicproxy.TimeCglibDynamicProxyHandler;
import com.dayue.pattern.proxy.dynamicproxy.TimeJdkDynamicProxyHandler;
import com.dayue.pattern.proxy.service.IAFunction;
import com.dayue.pattern.proxy.service.IBFunction;
import com.dayue.pattern.proxy.service.impl.IAFuntionImpl;
import com.dayue.pattern.proxy.service.impl.IBFuntionImpl;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * @author zhengdayue
 */
public class Main {

    public static void main(String[] args) {
        //jdk动态代理生成的代理类输出到项目根目录
        System.getProperties().put( "sun.misc.ProxyGenerator.saveGeneratedFiles" , "true" );
        IAFuntionImpl a = new IAFuntionImpl();
        TimeJdkDynamicProxyHandler aHandler = new TimeJdkDynamicProxyHandler(a);
        IAFunction aProxy = (IAFunction)Proxy.newProxyInstance(a.getClass().getClassLoader(), a.getClass().getInterfaces(), aHandler);
        aProxy.doA();

        IBFuntionImpl b = new IBFuntionImpl();
        TimeJdkDynamicProxyHandler bHandler = new TimeJdkDynamicProxyHandler(b);
        IBFunction bProxy = (IBFunction) Proxy.newProxyInstance(b.getClass().getClassLoader(), b.getClass().getInterfaces(), bHandler);
        bProxy.doB();

        //使用spring cglib动态代理
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(IAFuntionImpl.class);
        enhancer.setCallback(new TimeCglibDynamicProxyHandler());
        IAFunction cglibAproxy = (IAFunction) enhancer.create();
        cglibAproxy.doA();

        enhancer.setSuperclass(IBFuntionImpl.class);
        enhancer.setCallback(new TimeCglibDynamicProxyHandler());
        IBFunction cglibBProxy = (IBFunction) enhancer.create();
        cglibBProxy.doB();
    }
}
