package com.dayue.cache.service.impl;

import com.dayue.cache.domain.ShopItemInfo;
import com.dayue.cache.repository.ShopItemInfoRepository;
import com.dayue.cache.service.ShopItemInfoService;
import com.dayue.cache.vo.ShopItemInfoVO;
import com.dayue.cache.vo.request.ShopItemInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author zhengdayue
 */
@Service
public class ShopItemInfoServiceImpl implements ShopItemInfoService {

    @Autowired
    private ShopItemInfoRepository shopItemInfoRepository;

    @Override
    public void create(ShopItemInfoRequest request) {
        shopItemInfoRepository.save(toShopItemInfo(request));
    }

    @Override
    public ShopItemInfoVO detail(Long id) {
        ShopItemInfo shopItemInfo = shopItemInfoRepository.findById(id).orElse(null);
        return toShopItemInfoVO(shopItemInfo);
    }

    @Override
    public ShopItemInfoVO updatePrice(Long id, BigDecimal price) {
        ShopItemInfo shopItemInfo = shopItemInfoRepository.findById(id).orElse(null);
        if (shopItemInfo != null) {
            shopItemInfo.setPrice(price);
            ShopItemInfo save = shopItemInfoRepository.save(shopItemInfo);
            return toShopItemInfoVO(save);
        }
        return null;
    }

    private ShopItemInfo toShopItemInfo(ShopItemInfoRequest request) {
        ShopItemInfo shopItemInfo = new ShopItemInfo();
        shopItemInfo.setName(request.getName());
        shopItemInfo.setTitle(request.getTitle());
        shopItemInfo.setPrice(request.getPrice());
        return shopItemInfo;
    }

    private ShopItemInfoVO toShopItemInfoVO(ShopItemInfo shopItemInfo) {
        if (shopItemInfo != null) {
            ShopItemInfoVO shopItemInfoVo = new ShopItemInfoVO();
            shopItemInfoVo.setId(shopItemInfo.getId());
            shopItemInfoVo.setName(shopItemInfo.getName());
            shopItemInfoVo.setTitle(shopItemInfo.getTitle());
            shopItemInfoVo.setPrice(shopItemInfo.getPrice());
            return shopItemInfoVo;
        }
        return null;
    }
}
