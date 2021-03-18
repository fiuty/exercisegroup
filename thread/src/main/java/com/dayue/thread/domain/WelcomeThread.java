package com.dayue.thread.domain;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengdayue
 */
@Slf4j
public class WelcomeThread extends Thread{
    @Override
    public void run() {
        log.info("创建线程方式一,线程名：{},继承Thread类,覆盖父run方法,实际Thread类也实现了Runnable接口的run方法",
                Thread.currentThread().getName());
    }
}
