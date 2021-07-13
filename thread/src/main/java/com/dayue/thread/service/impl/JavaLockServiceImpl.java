package com.dayue.thread.service.impl;

import com.dayue.thread.service.JavaLockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhengdayue
 */
@Service
@Slf4j
public class JavaLockServiceImpl implements JavaLockService {

    private static int a = 0;

    private static int b = 0;

    @Override
    public void synKeywordLock() {
        for (int i = 0; i < 10; i++) {
            Runnable runnable = this::increaseA;
            new Thread(runnable).start();
        }
    }

    @Override
    public void interfaceLock() {
        for (int i = 0; i < 10; i++) {
            Runnable runnable = this::increaseB;
            new Thread(runnable).start();
        }
    }

    //隐式锁，无需try catch释放锁，但没lock接口实现锁灵活
    private synchronized void increaseA() {
        a++;
        if (a == 10) {
            log.info("a:{}", a);
        }
    }

    //需try catch释放锁
    private void increaseB() {
        ReentrantLock reentrantLock = new ReentrantLock();
        try {
            reentrantLock.lock();
            b++;
            if (b == 10) {
                log.info("b:{}", b);
            }
        } catch (Exception e) {
            reentrantLock.unlock();
        }
    }

    public JavaLockServiceImpl() {

    }
}
