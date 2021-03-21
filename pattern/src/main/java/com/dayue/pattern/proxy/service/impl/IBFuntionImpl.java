package com.dayue.pattern.proxy.service.impl;


import com.dayue.pattern.proxy.service.IBFunction;

/**
 * @author zhengdayue
 */
public class IBFuntionImpl implements IBFunction {

    @Override
    public void doB() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("线程睡眠异常");
        }
    }
}
