package com.dayue.common.pojo;

/**
 * 前端响应体
 *
 * @author zhengdayue
 * @time 2022/3/24 16:26
 */
public class BaseResponse<T>{

    private static final int CODE_SUCCESS = 200;

    private static final int STATUS_SUCCESS = 2000000;

    private static final int CODE_ERROR = 500;

    private int status;

    private int code;

    private String msg;

    private T data;

    private BaseResponse(int status, String msg, T data) {
        if(CODE_SUCCESS==status){
            this.setStatus(STATUS_SUCCESS);
        }else{
            this.setStatus(status);
        }
        this.setCode(status);
        this.setMsg(msg);
        this.setData(data);
    }

    public BaseResponse() {
    }

    public static <T> BaseResponse<T> success() {
        return new BaseResponse<T>(CODE_SUCCESS, "success", null);
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<T>(CODE_SUCCESS, "success", data);
    }

    public static <T> BaseResponse<T> success(String message, T data) {
        return new BaseResponse<T>(CODE_SUCCESS, message, data);
    }

    public static <T> BaseResponse<T> error() {
        return new BaseResponse<T>(CODE_ERROR, "fail", null);
    }

    public static <T> BaseResponse<T> error(T data) {
        return new BaseResponse<T>(CODE_ERROR, "fail", data);
    }

    public static <T> BaseResponse<T> error(String message, T data) {
        return new BaseResponse<T>(CODE_ERROR, message, data);
    }


    public static <T> BaseResponse<T> error(Integer code, String message) {
        return new BaseResponse<T>(code, message, null);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
