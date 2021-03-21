package com.dayue.domain;

/**
 * 审核人：未审核
 *
 * @author 郑达悦
 */
public class LoanFinanceProduct extends AbstractFinanceProduct {

    @Override
    public void disPlayProduct() {
        System.out.println("贷款产品");
    }
}
