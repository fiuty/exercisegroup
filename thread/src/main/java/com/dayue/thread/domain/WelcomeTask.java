package com.dayue.thread.domain;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengdayue
 */
@Slf4j
public class WelcomeTask implements Runnable{
    @Override
    public void run() {
        log.info("创建线程方式二,线程名：{},实现Runnable接口,实现run方法", Thread.currentThread().getName());
    }
}
