package com.dayue.constants;

/**
 * 产品名称枚举
 *
 * @author 郑达悦
 */
public enum ProductEnum {
    /**
     * 产品类别
     */
    Bond("Bond"), Loan("Loan"), Stock("Stock");

    String name;

    ProductEnum(String name) {
        this.name = name;
    }
}
