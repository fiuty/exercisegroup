package com.dayue.common.exception;

/**
 * 自定义业务异常
 *
 * @author zhengdayue
 * @time 2022/3/24 16:17
 */
public class BussinessException extends RuntimeException {

    public BussinessException(String message) {
        super(message);
    }
}
