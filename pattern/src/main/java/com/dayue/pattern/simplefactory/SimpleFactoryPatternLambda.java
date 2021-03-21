package com.dayue.pattern.simplefactory;


import com.dayue.constants.ProductEnum;
import com.dayue.domain.AbstractFinanceProduct;

/**
 * 用lambda的方式实现简单工厂模式
 *
 * @author 郑达悦
 */
public class SimpleFactoryPatternLambda {
    public static void main(String[] args) {
        AbstractFinanceProduct bondProduct1 = SimpleFactoryLambda.creatProduct(ProductEnum.Bond);
        AbstractFinanceProduct bondProduct2 = SimpleFactoryLambda.creatProduct(ProductEnum.Bond);
        AbstractFinanceProduct stockProduct = SimpleFactoryLambda.creatProduct(ProductEnum.Stock);
        System.out.println("每次所创建的对象是否相等："+bondProduct1.equals(bondProduct2));
        stockProduct.disPlayProduct();
    }
}
