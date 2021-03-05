package com.dayue.cache.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhengdayue
 */
@Data
public class ShopItemInfoVO implements Serializable {

    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 单位元,精确两位小数
     */
    private BigDecimal price;
}
