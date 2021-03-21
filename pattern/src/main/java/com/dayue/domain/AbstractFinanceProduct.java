package com.dayue.domain;

/**
 * 抽象金融产品类
 *
 * @author 郑达悦
 */
public abstract class AbstractFinanceProduct {

    /**
     * 所有产品类的公共业务方法
     */
    public void methodSame() {
        String className = this.getClass().getName();
        //公共方法的实现
        System.out.println("抽象类公共方法,该类是：" + className);
    }

    /**
     * 声明抽象业务方法
     */
    public abstract void disPlayProduct();
}
