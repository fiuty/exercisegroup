package com.dayue.pattern.strategy;


import com.dayue.constants.ParcelCompanyEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 针对不同的快递公司计算邮费
 * @author Fiuty
 */
public class CalculatePostage {

     Map<ParcelCompanyEnum, Function<Integer, Double>> map = new HashMap<>(5);

    {
        map.put(ParcelCompanyEnum.JD, this::calculateJd);
        map.put(ParcelCompanyEnum.STO, this::calculatSto);
        map.put(ParcelCompanyEnum.YTO, this::calculateYto);
        map.put(ParcelCompanyEnum.ZTO, this::calculateZto);
    }

    public Double calculatePostage1(ParcelCompanyEnum company, Integer weight) {
        if (company == ParcelCompanyEnum.JD) {
            return 10 + weight * 1.2;
        } else if (company == ParcelCompanyEnum.STO) {
            return 12 + weight * 0.8;
        } else if (company == ParcelCompanyEnum.YTO) {
            return 8 + weight * 1.5;
        } else if (company == ParcelCompanyEnum.ZTO) {
            return 9 + weight * 1.1;
        } else {
            throw new IllegalArgumentException("No such company :" + company);
        }
    }

    public Double calculatePostage2(ParcelCompanyEnum company, Integer weight) {
        switch (company) {
            case JD: return 10 + weight * 1.2;
            case STO: return 12 + weight * 0.8;
            case YTO: return 8 + weight * 1.5;
            case ZTO: return 9 + weight * 1.1;
            default: throw new IllegalArgumentException("No such company :"+company);
        }
    }

    public Double calculateJd(Integer weight) {
        return 10 + weight * 1.2;
    }

    public Double calculatSto(Integer weight) {
        return 12 + weight * 0.8;
    }

    public Double calculateYto(Integer weight) {
        return 8 + weight * 1.5;
    }

    public Double calculateZto(Integer weight) {
        return 9 + weight * 1.1;
    }

    public Double calculatePostage3(ParcelCompanyEnum company, Integer weight) {
        CalculateFactory factory = new CalculateFactory();
        return factory.creat(company).calculate(weight);
    }
}
