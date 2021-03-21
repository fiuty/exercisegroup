package com.dayue.pattern.proxy.staticproxy;



import com.dayue.pattern.proxy.service.IAFunction;
import com.dayue.pattern.proxy.service.IBFunction;
import com.dayue.pattern.proxy.service.impl.IAFuntionImpl;
import com.dayue.pattern.proxy.service.impl.IBFuntionImpl;

import java.time.Duration;
import java.time.Instant;

/**
 * @author zhengdayue
 */
public class TimeConsumeStaticProxy implements IAFunction, IBFunction {

    private IAFuntionImpl iaFuntion;

    private IBFuntionImpl ibFuntion;

    TimeConsumeStaticProxy(IAFuntionImpl iaFuntion, IBFuntionImpl ibFuntion) {
        this.iaFuntion = iaFuntion;
        this.ibFuntion = ibFuntion;
    }

    @Override
    public void doA() {
        Instant now = Instant.now();
        iaFuntion.doA();
        System.out.println("静态代理用时：" + Duration.between(now, Instant.now()).toMillis()+"ms");
    }

    @Override
    public void doB() {
        Instant now = Instant.now();
        ibFuntion.doB();
        System.out.println("静态代理用时：" + Duration.between(now, Instant.now()).toMillis()+"ms");
    }
}
