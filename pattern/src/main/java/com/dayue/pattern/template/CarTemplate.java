package com.dayue.pattern.template;


import com.dayue.domain.Car;

/**
 * @author Fiuty
 */
public abstract class CarTemplate {

    final void makeCar(Car car) {
        System.out.println(car.hashCode()+"-正在制作轮子-1");
        car.setWheel(4);
        System.out.println(car.hashCode()+"-轮子制造完成-2");

        System.out.println(car.hashCode()+"-正在制作后视镜-3");
        car.setMirror(2);
        System.out.println(car.hashCode()+"-后视镜制造完成-4");

        System.out.println(car.hashCode()+"-正在制作车身-5");
        car.setCarBody(true);
        System.out.println(car.hashCode()+"-车身制造完成-6");

        makeColor(car);
    }

    abstract void makeColor(Car car);
}
