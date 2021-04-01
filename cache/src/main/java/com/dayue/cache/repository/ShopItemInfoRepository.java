package com.dayue.cache.repository;

import com.dayue.cache.domain.ShopItemInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author zhengdayue
 */
public interface ShopItemInfoRepository extends JpaRepository<ShopItemInfo,Long>, JpaSpecificationExecutor<ShopItemInfo> {

    ShopItemInfo findByName(String name);
}
