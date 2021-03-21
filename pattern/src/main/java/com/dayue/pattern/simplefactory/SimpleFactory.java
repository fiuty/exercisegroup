package com.dayue.pattern.simplefactory;


import com.dayue.constants.ProductEnum;
import com.dayue.domain.AbstractFinanceProduct;
import com.dayue.domain.BondFinanceProduct;
import com.dayue.domain.LoanFinanceProduct;
import com.dayue.domain.StockFinanceProduct;

/**
 * 简单工厂类
 *
 * @author 郑达悦
 */
class SimpleFactory {

    static AbstractFinanceProduct creatProduct(ProductEnum productEnum) {
        switch (productEnum) {
            case Bond :
                return new BondFinanceProduct();
            case Loan :
                return new LoanFinanceProduct();
            case Stock :
                return new StockFinanceProduct();
            default:
                throw new RuntimeException("No such product" + productEnum.name());
        }
    }
}
