package com.dayue.pattern.template;

import com.dayue.constants.ColorEnum;
import com.dayue.domain.Car;

/**
 * @author Fiuty
 */
public class MakeWhiteCar extends CarTemplate{
    @Override
    void makeColor(Car car) {
        car.setColor(ColorEnum.WHITE.getColor());
    }
}
