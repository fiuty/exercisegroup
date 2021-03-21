package com.dayue.pattern.template;

import com.dayue.constants.ColorEnum;
import com.dayue.domain.Car;

/**
 * @author Fiuty
 */
public class TemplateMain {

    public static void main(String[] args) {
        Car car1 = new Car();
        MakeCarUtils.makeWheel(car1);
        MakeCarUtils.makeMirror(car1);
        MakeCarUtils.makeCarBody(car1);
        car1.setColor(ColorEnum.BLACK.getColor());
        System.out.println(car1);

        Car car2 = new Car();
        MakeCarUtils.makeWheel(car2);
        MakeCarUtils.makeMirror(car2);
        MakeCarUtils.makeCarBody(car2);
        car2.setColor(ColorEnum.WHITE.getColor());
        System.out.println(car2);

        Car car3 = new Car();
        MakeCarUtils.makeWheel(car3);
        MakeCarUtils.makeMirror(car3);
        MakeCarUtils.makeCarBody(car3);
        car3.setColor(ColorEnum.RED.getColor());
        System.out.println(car3);

        Car wantWhiteCar = new Car();
        Car wantBlackCar = new Car();
        Car wantRedCar = new Car();
        CarTemplate templateWhiteCar = new MakeWhiteCar();
        CarTemplate templateBlackCar = new MakeBlackCar();
        CarTemplate templateRedCar = new MakeRedCar();
        templateWhiteCar.makeCar(wantWhiteCar);
        templateBlackCar.makeCar(wantBlackCar);
        templateRedCar.makeCar(wantRedCar);
        System.out.println(wantWhiteCar);
        System.out.println(wantBlackCar);
        System.out.println(wantRedCar);

        Car whiteCar = new Car();
        Car blackCar = new Car();
        LambdaCarTemplate.makeCar(whiteCar, (Car car) -> car.setColor(ColorEnum.WHITE.getColor()));
        LambdaCarTemplate.makeCar(blackCar, (Car car) -> car.setColor(ColorEnum.BLACK.getColor()));
        System.out.println(whiteCar);
        System.out.println(blackCar);


        Car white = new Car();
        Car red = new Car();
        LambdaCarTemplate.makeCar(white, LambdaCarTemplate.makeWhiteCar());
        LambdaCarTemplate.makeCar(red, LambdaCarTemplate.makeRedCar());
        System.out.println(white);
        System.out.println(red);

        Car makeWhite = new Car();
        Car makeRed = new Car();
        LambdaCarTemplate.makeCar(makeWhite, LambdaCarTemplate::makeWhiteCar);
        LambdaCarTemplate.makeCar(makeRed, LambdaCarTemplate::makeRedCar);
        System.out.println(makeWhite);
        System.out.println(makeRed);
    }
}
