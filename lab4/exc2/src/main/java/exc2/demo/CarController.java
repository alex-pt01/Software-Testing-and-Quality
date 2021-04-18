package exc2.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {
    @Autowired
    private CarManagerService carManagerService;

    @PostMapping("/cars") public ResponseEntity<Car> createCar(@ResponseBody Car car){
        HttpStatus status = HttpStatus.CREATED;
        Car saveCar = carManagerService.save(car);
        return new ResponseEntity<>(saveCar, status);
    }

    @GetMapping(path="/cars", produces = "application/json")
    public List<Car> getAllCars(){
        return carManagerService.getAllCars();
    }
    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value="id") Long carId)
        throws ResourceNotFoundException {
        Car car = carManagerService.getCarDetails(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not founded"));
        return ResponseEntity.ok().body(car);

    }

}
