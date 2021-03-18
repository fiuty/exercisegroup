package com.dayue.thread;


import com.dayue.thread.domain.WelcomeTask;
import com.dayue.thread.domain.WelcomeThread;
import com.dayue.thread.domain.WorkerThread;
import com.dayue.thread.service.ThreadService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
class ThreadApplicationTests {

    @Autowired
    private ThreadService threadService;

    @Test
    void contextLoads() {
    }

    //创建线程的两种方式
    @Test
    public void testThread() {
        WelcomeThread welcomeThread = new WelcomeThread();
        welcomeThread.start();

        Thread welcomeTask = new Thread(new WelcomeTask());
        welcomeTask.start();

        log.info("当前线程名：{}",Thread.currentThread().getName());
    }

    //创建线程两种方式的区别
    @Test
    public void testDiff() {
        threadService.testDiff();
    }

    @Test
    public void testContest() {
        // 客户端线程数
        int numberOfThreads = 4;
        Thread[] workerThreads = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            workerThreads[i] = new WorkerThread(i, 10);
        }
        // 待所有线程创建完毕后，再一次性将其启动，以便这些线程能够尽可能地在同一时间内运行
        for (Thread ct : workerThreads) {
            ct.start();
        }
    }

}
