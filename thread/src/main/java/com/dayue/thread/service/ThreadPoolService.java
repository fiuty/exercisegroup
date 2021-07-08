package com.dayue.thread.service;

import java.util.concurrent.ExecutionException;

/**
 * @author zhengdayue
 */
public interface ThreadPoolService {

    //固定线程池
    void fixedThreadPool();

    //缓存线程池
    void cachedThreadPool();

    //周期线程池
    void scheduledThreadPool();

    //唯一线程池
    void singleTheadExecutor();

    //唯一周期线程池
    void singleThreadScheduledExecutor();

    //Java8ForkJoinPool线程池
    void forkJoinPool() throws ExecutionException, InterruptedException;

    //自定义线程池
    void defineThreadPool();
}
