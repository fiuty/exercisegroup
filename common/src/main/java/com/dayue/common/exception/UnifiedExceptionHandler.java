package com.dayue.common.exception;

import com.dayue.common.pojo.BaseResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常切面
 *
 * @author zhengdayue
 * @time 2022/3/24 16:26
 */
@RestControllerAdvice
public class UnifiedExceptionHandler {


    /**
     * 捕获处理业务异常
     *
     * @param bussinessException 自定义业务异常
     * @return 结果
     */
    @ExceptionHandler(BussinessException.class)
    public BaseResponse<String> handleBusinessException(BussinessException bussinessException) {
        return BaseResponse.error(bussinessException.getMessage());
    }
}
