package com.dayue.thread.main;

/**
 * @author zhengdayue
 */
public class ThreadLocal_Main {

    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        new Thread(() -> {
            threadLocal.set(10);
        }).start();
    }
}
