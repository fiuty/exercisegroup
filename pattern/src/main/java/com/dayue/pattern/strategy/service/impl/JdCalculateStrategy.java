package com.dayue.pattern.strategy.service.impl;


import com.dayue.pattern.strategy.service.CalculateStrategy;

/**
 * 计算京东邮费
 * @author Fiuty
 */
public class JdCalculateStrategy implements CalculateStrategy {

    @Override
    public Double calculate(Integer weight) {
        return 10 + weight * 1.2;
    }
}
