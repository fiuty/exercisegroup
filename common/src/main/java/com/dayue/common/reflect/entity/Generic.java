package com.dayue.common.reflect.entity;

/**
 * 泛型类
 *
 * @author zhengdayue
 */
public class Generic<T> {
    T tObject;

    public T gettObject() {
        return tObject;
    }

    public void settObject(T tObject) {
        this.tObject = tObject;
    }
}
