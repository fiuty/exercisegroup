package com.dayue.thread.main;

import com.dayue.thread.domain.WorkerThread;
import com.dayue.thread.util.Tools;

/**
 * @author zhengdayue
 */
public class Main {

    // 线程间的共享变量
    static int data = 0;

    //线程原子性问题
    public static void main(String[] args) {

    }

    //线程原子性问题
    public static void atomicity() {
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

    //线程可见性，可能输出的是1也可能是2
    public static void visibility() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                // 使当前线程休眠R毫秒（R的值为随机数）
                Tools.randomPause(50);
                // 读取并打印变量data的值
                System.out.println(data);
            }
        };
        // 在子线程thread启动前更新变量data的值
        data = 1; // 语句①
        thread.start();
        // 使当前线程休眠R毫秒（R的值为随机数）
        Tools.randomPause(50);
        // 在子线程thread启动后更新变量data的值
        data = 2; // 语句②
    }

}
