package com.dayue.cache.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 商品信息表
 * @author zhengdayue
 */
@Table
@Entity
@Data
public class ShopItemInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
