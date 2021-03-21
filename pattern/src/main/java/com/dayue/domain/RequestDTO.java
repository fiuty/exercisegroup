package com.dayue.domain;

/**
 * 请求报文,T 携带简单工厂所创建的不同对象
 * @author Fiuty
 */
public class RequestDTO <T>{

    private T payload;

    @Override
    public String toString() {
        return "RequestDTO{" +
                "payload=" + payload +
                '}';
    }

    public RequestDTO(T payload) {
        this.payload = payload;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
