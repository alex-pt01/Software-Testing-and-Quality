package com.ua.car_TQS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarManagerService {

    @Autowired
    private CarRepository carRepository;

    public Car save(Car car){return carRepository.save(car);}

    public List<Car> getAllCars(){return carRepository.findAll();}
    public Car getCarDetails(Long id){return carRepository.findByCarId(id);}

}
