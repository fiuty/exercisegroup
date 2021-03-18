package com.dayue.thread.service.impl;

import com.dayue.thread.domain.CountingTask;
import com.dayue.thread.domain.CountingThread;
import com.dayue.thread.service.ThreadService;
import org.springframework.stereotype.Service;

/**
 * @author zhengdayue
 */
@Service
public class ThreadServiceImpl implements ThreadService {


    @Override
    public void testDiff() {
        Thread t;
        CountingTask ct = new CountingTask();
        for (int i = 0; i < 8; i++) {

            // 直接创建线程
            new Thread(ct).start();
        }
        for (int i = 0; i < 8; i++) {

            // 以子类的方式创建线程
            t = new CountingThread();

            t.start();
        }
    }
}
