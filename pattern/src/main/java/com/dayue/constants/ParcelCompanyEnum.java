package com.dayue.constants;

/**
 *
 * 快递公司枚举
 * @author Fiuty
 */
public enum ParcelCompanyEnum {

    ZTO("中通快递"),YTO("圆通快递"),STO("申通快递"),JD("京东快递");

    String name;

    ParcelCompanyEnum(String name) {
        this.name = name;
    }
}
