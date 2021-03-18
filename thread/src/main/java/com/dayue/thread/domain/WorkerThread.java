package com.dayue.thread.domain;

import com.dayue.thread.service.impl.RequestIDGenerator;
import com.dayue.thread.util.Tools;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengdayue
 */
@Slf4j
public class WorkerThread  extends Thread{
    private final int requestCount;

    public WorkerThread(int id, int requestCount) {
        super("worker-" + id);
        this.requestCount = requestCount;
    }


    @Override
    public void run() {
        int i = requestCount;
        String requestID;
        RequestIDGenerator requestIDGen = RequestIDGenerator.getInstance();
        while (i-- > 0) {
            // 生成Request ID
            requestID = requestIDGen.nextID();
            processRequest(requestID);
        }
    }

    // 模拟请求处理
    private void processRequest(String requestID) {
        // 模拟请求处理耗时
        //Tools.randomPause(50);
        log.info("{} got requestID: {} ", Thread.currentThread().getName(), requestID);
    }
}
