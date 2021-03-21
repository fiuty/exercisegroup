package com.dayue.constants;

/**
 * 车油漆颜色
 * @author Fiuty
 */
public enum ColorEnum {

    WHITE("白色"),BLACK("黑色"),RED("红色");

    String color;

    ColorEnum(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
