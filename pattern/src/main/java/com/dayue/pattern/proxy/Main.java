package com.dayue.pattern.proxy;

import com.dayue.pattern.proxy.dynamicproxy.TimeDynamicProxyHandler;
import com.dayue.pattern.proxy.service.IAFunction;
import com.dayue.pattern.proxy.service.IBFunction;
import com.dayue.pattern.proxy.service.impl.IAFuntionImpl;
import com.dayue.pattern.proxy.service.impl.IBFuntionImpl;

import java.lang.reflect.Proxy;

/**
 * @author zhengdayue
 */
public class Main {

    public static void main(String[] args) {
        //动态代理生成的代理类输出到项目根目录
        System.getProperties().put( "sun.misc.ProxyGenerator.saveGeneratedFiles" , "true" );
        IAFuntionImpl a = new IAFuntionImpl();
        TimeDynamicProxyHandler aHandler = new TimeDynamicProxyHandler(a);
        IAFunction aProxy = (IAFunction)Proxy.newProxyInstance(a.getClass().getClassLoader(), a.getClass().getInterfaces(), aHandler);
        aProxy.doA();

        IBFuntionImpl b = new IBFuntionImpl();
        TimeDynamicProxyHandler bHandler = new TimeDynamicProxyHandler(b);
        IBFunction bProxy = (IBFunction) Proxy.newProxyInstance(b.getClass().getClassLoader(), b.getClass().getInterfaces(), bHandler);
        bProxy.doB();

    }
}
