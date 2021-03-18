package com.dayue.thread.domain;

import com.dayue.thread.util.Tools;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengdayue
 */
@Slf4j
public class CountingThread extends Thread {

    private Counter counter = new Counter();

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            doSomething();
            counter.increment();
        }
        log.info("CountingThread:{}", counter.value());
    }

    private void doSomething() {
        // 使当前线程休眠随机时间
        Tools.randomPause(80);
    }

}
