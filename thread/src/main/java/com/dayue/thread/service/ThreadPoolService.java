package com.dayue.thread.service;

import java.util.concurrent.ExecutionException;

/**
 * @author zhengdayue
 */
public interface ThreadPoolService {

    //固定线程池：阻塞队列为无界阻塞队列，需小心oom内存溢出，队列堆满内存。
    void fixedThreadPool();

    //缓存线程池：无限制创建线程数量，当任务特别多可能会超出操作系统上线或者导致内存不足
    void cachedThreadPool();

    //周期线程池：延迟阻塞队列，按照时间入队，也是无界阻塞队列，任务堆积oom
    void scheduledThreadPool();

    //唯一线程池：核心线程数只有1，但是阻塞队列为无界阻塞队列，任务堆积oom风险。
    void singleTheadExecutor();

    //唯一周期线程池：无界阻塞队列，任务堆积oom风险。
    void singleThreadScheduledExecutor();

    //Java8ForkJoinPool线程池
    void forkJoinPool() throws ExecutionException, InterruptedException;

    //自定义Java线程池
    void defineJavaThreadPool();

    //自定义Spring线程池
    void defineSpringThreadPool();
}
