package com.dayue.pattern.template;

import com.dayue.constants.ColorEnum;
import com.dayue.domain.Car;

import java.util.function.Consumer;

/**
 * @author Fiuty
 */
public class LambdaCarTemplate {

    public static void makeCar(Car car, Consumer<Car> consumer) {
        System.out.println(car.hashCode()+"-正在制作轮子-1");
        car.setWheel(4);
        System.out.println(car.hashCode()+"-轮子制造完成-2");
        System.out.println(car.hashCode()+"-正在制作后视镜-3");
        car.setMirror(2);
        System.out.println(car.hashCode()+"-后视镜制造完成-4");
        System.out.println(car.hashCode()+"-正在制作车身-5");
        car.setCarBody(true);
        System.out.println(car.hashCode()+"-车身制造完成-6");
        consumer.accept(car);
    }

    public static Consumer<Car> makeBlackCar() {
        return (Car car) -> car.setColor(ColorEnum.BLACK.getColor());
    }

    public static Consumer<Car> makeWhiteCar() {
        return (Car car) -> car.setColor(ColorEnum.WHITE.getColor());
    }

    public static Consumer<Car> makeRedCar() {
        return (Car car) -> car.setColor(ColorEnum.RED.getColor());
    }

    public static void makeWhiteCar(Car car) {
        car.setColor(ColorEnum.WHITE.getColor());
    }

    public static void makeRedCar(Car car) {
        car.setColor(ColorEnum.RED.getColor());
    }

    public static void makeBlackCar(Car car) {
        car.setColor(ColorEnum.BLACK.getColor());
    }
}
