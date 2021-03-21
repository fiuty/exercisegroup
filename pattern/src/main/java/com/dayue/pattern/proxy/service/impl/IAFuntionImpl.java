package com.dayue.pattern.proxy.service.impl;

import com.dayue.pattern.proxy.service.IAFunction;

/**
 * @author zhengdayue
 */
public class IAFuntionImpl  implements IAFunction {

    @Override
    public void doA() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("线程睡眠异常");
        }
    }
}
