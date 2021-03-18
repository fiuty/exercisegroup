package com.dayue.thread.service.impl;

import com.dayue.thread.service.CircularSeqGenerator;

/**
 * @author zhengdayue
 */
public class RequestIDGenerator  implements CircularSeqGenerator {

    //保存该类的唯一实例
    private final static RequestIDGenerator INSTANCE = new RequestIDGenerator();

    private final static short SEQ_UPPER_LIMIT = 999;

    private short sequence = -1;

    // 私有构造器
    private RequestIDGenerator() {
    }

    //由于sequence++是读改写三步操作，以及if条件读和赋值写操作，多个线程共同访问共享变量sequence会产生竞态问题
    //故用synchronized修饰该方法nextSequence，保证同一时间只有一个线程能执行该方法。
    @Override
    public synchronized short nextSequence() {
        if (sequence >= SEQ_UPPER_LIMIT) {
            sequence = 0;
        } else {
            sequence++;
        }
        return sequence;
    }

    public String nextID() {
        // 生成请求序列号
        short sequenceNo = nextSequence();
        return "-----------" + sequenceNo;
    }

    public static RequestIDGenerator getInstance() {
        return INSTANCE;
    }
}
