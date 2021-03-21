package com.dayue.pattern.strategy.service.impl;


import com.dayue.pattern.strategy.service.CalculateStrategy;

/**
 * 计算申通邮费
 * @author Fiuty
 */
public class StoCalculateStrategy implements CalculateStrategy {
    @Override
    public Double calculate(Integer weight) {
        return 12 + weight * 0.8;
    }
}
