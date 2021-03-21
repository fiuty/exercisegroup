package com.dayue.pattern.simplefactory.service;


import com.dayue.domain.Animal;
import com.dayue.domain.People;
import com.dayue.domain.RequestDTO;

/**
 * @author Fiuty
 */
public interface SimpleFactoryAdvanceService {

    RequestDTO<People> createPeople(String name);

    RequestDTO<Animal> createAnimal(String name);
}
