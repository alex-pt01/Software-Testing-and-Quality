package com.ua.car_TQS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class CarManagerServiceTest {

    @Mock( lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carService;

    @BeforeEach
    public void setUp() {
        Car toyota = new Car("toyota", "corolla");
        Car peugeot = new Car("peugeot", "308");
        Car mini = new Car("mini", "cooper");

        List<Car> allCars = Arrays.asList(toyota,peugeot,mini);

        Mockito.when(carRepository.findByCarId(toyota.getCarId())).thenReturn(toyota);
        Mockito.when(carRepository.findAll()).thenReturn(allCars);
        Mockito.when(carRepository.findByCarId(-100L)).thenReturn(null);
    }


    @Test
    public void whenValidId_thenCarShouldBeFound() {
        Car fromDb = carService.getCarDetails(1L);
        assertThat(fromDb.getMaker()).isEqualTo("toyota");

        verifyFindByIdIsCalledOnce();
    }

    @Test
    public void whenInValidId_thenCarShouldNotBeFound() {
        Car fromDb = carService.getCarDetails(-5L);
        verifyFindByIdIsCalledOnce();
        assertThat(fromDb).isNull();
    }

    @Test
    public void given3Cars_whengetAll_thenReturn3Records() {
        Car toyota = new Car("toyota", "corolla");
        Car peugeot = new Car("peugeot", "308");
        Car mini = new Car("mini", "cooper");

        List<Car> allCars = carService.getAllCars();
        verifyFindAllCarsIsCalledOnce();
        assertThat(allCars).hasSize(3).extracting(Car::getMaker).contains(toyota.getMaker(), peugeot.getMaker(), mini.getMaker());
    }

    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findByCarId(Mockito.anyLong());
    }

    private void verifyFindAllCarsIsCalledOnce() {
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findAll();
    }
}