package com.dayue.thread.domain;

/**
 * @author zhengdayue
 */
public class Counter {
    private int count = 0;

    public void increment() {
        count++;
    }

    public int value() {
        return count;
    }
}
