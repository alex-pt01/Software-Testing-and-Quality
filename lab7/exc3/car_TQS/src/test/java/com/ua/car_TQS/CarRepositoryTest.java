package com.ua.car_TQS;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;



    @Test
    public void whenFindCarByExistingId_thenReturnCar() {
        Car toyota = new Car("toyota", "corolla");
        entityManager.persistAndFlush(toyota);

        Car fromDb = carRepository.findByCarId(toyota.getCarId());
        assertThat(fromDb).isNotNull();
        assertThat(fromDb.getModel()).isEqualTo( toyota.getModel());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Car fromDb = carRepository.findByCarId(-111L);
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        Car toyota = new Car("toyota", "corolla");
        Car peugeot = new Car("peugeot", "308");
        Car mini = new Car("mini", "cooper");

        entityManager.persist(toyota);
        entityManager.persist(peugeot);
        entityManager.persist(mini);
        entityManager.flush();

        List<Car> allCars = carRepository.findAll();

        assertThat(allCars).hasSize(3).extracting(Car::getMaker).containsOnly(toyota.getMaker(),
                peugeot.getMaker(), mini.getMaker());
    }



}