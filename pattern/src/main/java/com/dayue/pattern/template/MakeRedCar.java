package com.dayue.pattern.template;

import com.dayue.constants.ColorEnum;
import com.dayue.domain.Car;

/**
 * @author Fiuty
 */
public class MakeRedCar extends CarTemplate{
    @Override
    void makeColor(Car car) {
        car.setColor(ColorEnum.RED.getColor());
    }
}
