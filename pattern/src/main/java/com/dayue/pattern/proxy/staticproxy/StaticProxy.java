package com.dayue.pattern.proxy.staticproxy;

import com.dayue.pattern.proxy.staticproxy.service.IFunction;
import com.dayue.pattern.proxy.staticproxy.service.impl.IFunctionImpl;

/**
 * @author zhengdayue
 */
public class StaticProxy implements IFunction {

    private IFunctionImpl iFunction;

    StaticProxy(IFunctionImpl iFunction) {
        this.iFunction = iFunction;
    }

    @Override
    public void doSomethin() {
        iFunction.doSomethin();
    }
}
