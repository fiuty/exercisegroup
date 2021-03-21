package com.dayue.pattern.strategy.service;

/**
 * 计算邮费接口
 * @author Fiuty
 */
public interface CalculateStrategy {
    Double calculate(Integer weight);
}
