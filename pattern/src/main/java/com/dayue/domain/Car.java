package com.dayue.domain;

/**
 * 车子
 * @author Fiuty
 */
public class Car {
    /**
     * 车轮子
     */
    private Integer wheel;

    /**
     * 镜子
     */
    private Integer mirror;

    /**
     * 车身
     */
    private Boolean carBody;

    /**
     * 车颜色
     */
    private String color;

    @Override
    public String toString() {
        return "Car{" +
                "wheel=" + wheel +
                ", mirror=" + mirror +
                ", carBody=" + carBody +
                ", color='" + color + '\'' +
                '}';
    }

    public Integer getWheel() {
        return wheel;
    }

    public void setWheel(Integer wheel) {
        this.wheel = wheel;
    }

    public Integer getMirror() {
        return mirror;
    }

    public void setMirror(Integer mirror) {
        this.mirror = mirror;
    }

    public Boolean getCarBody() {
        return carBody;
    }

    public void setCarBody(Boolean carBody) {
        this.carBody = carBody;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
