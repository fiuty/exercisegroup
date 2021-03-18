package com.dayue.thread.util;

import java.util.Random;

/**
 * @author zhengdayue
 */
public class Tools {

    private static final Random rnd = new Random();

    public static void randomPause(int maxPauseTime) {
        int sleepTime = rnd.nextInt(maxPauseTime);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
