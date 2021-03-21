package com.dayue.pattern.simplefactory;


import com.dayue.constants.ProductEnum;
import com.dayue.domain.AbstractFinanceProduct;

/**
 * 简单工厂模式(Simple Factory Pattern)：定义一个工厂类，它可以根据参数的不同返回不同类的实例，被创建的实例通常都具有共同的父类。
 * 因为在简单工厂模式中用于创建实例的方法是静态(static)方法，因此简单工厂模式又被称为静态工厂方法(Static Factory Method)模式，它属于类创建型模式。
 *
 * 工厂模式是最常用的一类创建型设计模式，通常我们所说的工厂模式是指工厂方法模式
 * 而简单工厂模式并不属于GoF 23个经典设计模式，但通常将它作为学习其他工厂模式的基础。
 *
 * 简单地说就是你需要什么，只需要传入给定格式正确的参数，就可以获取所需的对象，而无需其中的创建细节。
 * 其实设计模式也让代码结构清晰和规范，这点很重要。
 *
 * @author 郑达悦
 */
public class SimpleFactoryPattern {
    public static void main(String[] args) {
        AbstractFinanceProduct stockProduct = SimpleFactory.creatProduct(ProductEnum.Stock);
        AbstractFinanceProduct bondProduct = SimpleFactory.creatProduct(ProductEnum.Bond);
        stockProduct.methodSame();
        stockProduct.disPlayProduct();
        bondProduct.disPlayProduct();
    }
}
