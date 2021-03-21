package com.dayue.pattern.proxy.staticproxy.service.impl;

import com.dayue.pattern.proxy.staticproxy.service.IFunction;

/**
 * @author zhengdayue
 */
public class IFunctionImpl implements IFunction {

    @Override
    public void doSomethin() {
        System.out.println("do somethin");
    }
}
