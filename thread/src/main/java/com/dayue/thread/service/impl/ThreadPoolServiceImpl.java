package com.dayue.thread.service.impl;

import com.dayue.thread.service.ThreadPoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @author zhengdayue
 */
@Service
@Slf4j
public class ThreadPoolServiceImpl implements ThreadPoolService {

    @Override
    public void fixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Task());
        }
    }

    @Override
    public void cachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Task());
        }
    }

    @Override
    public void scheduledThreadPool() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        for (int i = 0; i < 100; i++) {
            scheduledExecutorService.schedule(new Task(), 10, TimeUnit.SECONDS);
        }
    }

    @Override
    public void singleTheadExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Task());
        }
    }

    @Override
    public void singleThreadScheduledExecutor() {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        for (int i = 0; i < 100; i++) {
            scheduledExecutorService.schedule(new Task(), 10, TimeUnit.SECONDS);
        }
    }

    @Override
    public void forkJoinPool() throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        for (int i = 0; i < 100; i++) {
            ForkJoinTask<Integer> submit = forkJoinPool.submit(new Fibonacci(i));
            log.info("num:{}", submit.get());
        }
    }

    @Override
    public void defineThreadPool() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(20);
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        threadPoolTaskExecutor.setQueueCapacity(10000);
        //拒绝策略,谁提交任务,那个提交任务的线程就负责完成溢出的任务。
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            log.info("Thread name:{}", Thread.currentThread().getName());
        }
    }

    static class Fibonacci extends RecursiveTask<Integer> {
        int n;

        public Fibonacci(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            }
            Fibonacci f1 = new Fibonacci(n - 1);
            Fibonacci f2 = new Fibonacci(n - 2);
            f1.fork();
            f2.fork();
            return f1.join() + f2.join();
        }
    }
}
