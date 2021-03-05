package com.dayue.cache.service;

import com.dayue.cache.vo.ShopItemInfoVO;
import com.dayue.cache.vo.request.ShopItemInfoRequest;

import java.math.BigDecimal;

/**
 * @author zhengdayue
 */
public interface ShopItemInfoService {

    /**
     * 创建商品
     * @param request 请求参数
     */
    void create(ShopItemInfoRequest request);

    /**
     * 商品详情
     * @param id 商品id
     * @return 详情
     */
    ShopItemInfoVO detail(Long id);

    /**
     * 更新商品价格
     * @param id 商品id
     * @param price 更新价格
     * @return 商品详情
     */
    ShopItemInfoVO updatePrice(Long id, BigDecimal price);
}
