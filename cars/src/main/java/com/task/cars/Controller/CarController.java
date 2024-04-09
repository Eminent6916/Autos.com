package com.task.cars.Controller;

import com.task.cars.Error.CarNotFoundException;
import com.task.cars.Model.Car;
import com.task.cars.Service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    @Autowired
    private CarService carService;

    @Value("${welcome.message}")
    private String welcomeMessage;

    @GetMapping("/")
    public String helloWorld (){
        return welcomeMessage;
    }

    @PostMapping("/api/car")
    public Car saveCar( @RequestBody Car car){
        return carService.saveCar(car);
    }

    @GetMapping("/api/car")
    public List <Car> fetchAllCars() throws CarNotFoundException {
        return carService.fetchAll();
    }

    @GetMapping("/api/car/{id}")
    public  Car fetchById(@PathVariable("id") Long id) throws CarNotFoundException {
        return carService.fetchById(id);
    }

    @PutMapping("/api/car/{id}")
    public Car updateCar(@PathVariable("id") Long id, @RequestBody Car car ) throws CarNotFoundException {
        return carService.updateCar(id, car);
    }
    @GetMapping("/api/car/name/{name}")
    public List <Car> fetchByName (@PathVariable("name") String name) throws CarNotFoundException {
        return carService.fetchByName(name);
    }
    @GetMapping("/api/car/made/{made}")
    public List <Car> fetchByMade(@PathVariable("made") String made) throws CarNotFoundException {
        return carService.fetchByMade(made);
    }
    @GetMapping("/api/car/{made}/{name}/{year}")
    public List <Car> fetchByMadeNameYear(@PathVariable("made") String made,
                                          @PathVariable("name") String name,
                                          @PathVariable("year") String year) throws CarNotFoundException {
        return carService.fetchByMadeNameYear(made,name,year);
    }
    @DeleteMapping("/api/car/{id}")
    public String deleteCar(@PathVariable("id") Long id) throws CarNotFoundException {
        return carService.deleteCar(id);
    }
}
