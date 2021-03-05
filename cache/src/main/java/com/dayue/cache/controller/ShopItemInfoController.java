package com.dayue.cache.controller;

import com.dayue.cache.service.ShopItemInfoService;
import com.dayue.cache.vo.ShopItemInfoVO;
import com.dayue.cache.vo.request.ShopItemInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @author zhengdayue
 */
@RestController
@RequestMapping("/api/shopItemInfo")
@CacheConfig(cacheNames = "shopItemInfo")
public class ShopItemInfoController {

    @Autowired
    private ShopItemInfoService shopItemInfoService;

    @PostMapping("/create")
    public void create(@RequestBody ShopItemInfoRequest request) {
        shopItemInfoService.create(request);
    }

    @GetMapping("/detail")
    @Cacheable(key = "#id")
    public ShopItemInfoVO detail(@RequestParam("id") Long id) {
        return shopItemInfoService.detail(id);
    }

    @PutMapping("/update/price")
    @CacheEvict(key = "#id")
    public ShopItemInfoVO updatePrice(@RequestParam("id") Long id, @RequestParam("price") BigDecimal price) {
        return shopItemInfoService.updatePrice(id, price);
    }
}
