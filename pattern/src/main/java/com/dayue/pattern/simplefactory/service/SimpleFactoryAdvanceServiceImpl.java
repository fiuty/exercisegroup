package com.dayue.pattern.simplefactory.service;


import com.dayue.domain.Animal;
import com.dayue.domain.People;
import com.dayue.domain.RequestDTO;

/**
 * @author Fiuty
 */
public class SimpleFactoryAdvanceServiceImpl implements SimpleFactoryAdvanceService {

    @Override
    public RequestDTO<People> createPeople(String name) {
        return new RequestDTO<>(new People(name));
    }

    @Override
    public RequestDTO<Animal> createAnimal(String name) {
        return new RequestDTO<>(new Animal(name));
    }
}
