package com.dayue.pattern.simplefactory;


import com.dayue.domain.Animal;
import com.dayue.domain.People;
import com.dayue.domain.RequestDTO;
import com.dayue.pattern.simplefactory.service.SimpleFactoryAdvanceServiceImpl;

/**
 * 简单工厂模式的另一种应用,在实际开发中可能会用到
 * 如果不想所创建的对象都是继承高度耦合的（实际也很少这样）,但在实际开发中又需要经常用到创建对象,可以试试下面这种方式
 * 这个是我在开发中所运用到的,项目线上需要大量调用淘宝的接口,淘宝请求参数的对象又有不同的属性
 * 每个接口的入参DTO是不一样的,故每次调用淘宝线上接口都需要创建入参对象,这些对象又不是高度耦合的,所以可以采用下面的这种方式。
 *
 * @author Fiuty
 */
public class SimpleFactoryPatternAdvance {

    public static void main(String[] agrs) {
        SimpleFactoryAdvanceServiceImpl factory = new SimpleFactoryAdvanceServiceImpl();
        RequestDTO<Animal> animalRequest = factory.createAnimal("特朗普");
        RequestDTO<People> peopleRequest = factory.createPeople("上帝");
        System.out.println("动物的名字是："+animalRequest.getPayload().getName());
        System.out.println("人类的名字是："+peopleRequest.getPayload().getName());
    }
}
