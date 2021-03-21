package com.dayue.pattern.strategy;

import com.dayue.constants.ParcelCompanyEnum;
import com.dayue.pattern.strategy.service.CalculateStrategy;
import com.dayue.pattern.strategy.service.impl.JdCalculateStrategy;
import com.dayue.pattern.strategy.service.impl.StoCalculateStrategy;
import com.dayue.pattern.strategy.service.impl.YtoCalculateStrategy;
import com.dayue.pattern.strategy.service.impl.ZtoCalculateStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂类，负责创建针对不同快递公司计算不同邮费的类
 * @author Fiuty
 */
public class CalculateFactory {

    private Map<ParcelCompanyEnum, CalculateStrategy> map = new HashMap<>();

    {
        map.put(ParcelCompanyEnum.JD, new JdCalculateStrategy());
        map.put(ParcelCompanyEnum.YTO, new YtoCalculateStrategy());
        map.put(ParcelCompanyEnum.ZTO, new ZtoCalculateStrategy());
        map.put(ParcelCompanyEnum.STO, new StoCalculateStrategy());
    }

    public CalculateStrategy creat(ParcelCompanyEnum company) {
        return map.get(company);
    }
}
