package exc2.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class CarManagerService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars;
    }

    public Car save(Car car) {
        carRepository.save(car);
        return car;
    }

    public Car getCarDetails(Long carID) {
        return carRepository.findByCarId(carID);

    }
}
