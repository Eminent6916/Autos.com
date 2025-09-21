package com.task.cars.Controller;

import com.task.cars.Constants.Messages;
import com.task.cars.Error.CarNotFoundException;
import com.task.cars.Model.Car;
import com.task.cars.Service.CarService;
import com.task.cars.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<String>> helloWorld() {
        return ResponseEntity.ok(ApiResponse.success(Messages.WELCOME_MSG, null));
    }
    @PostMapping("/api/car")
    public ResponseEntity<ApiResponse<Car>> saveCar(@RequestBody Car car) {
        try {
            Car savedCar = carService.saveCar(car);
            return ResponseEntity.ok(ApiResponse.success(Messages.CAR_CREATED, savedCar));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage(), null));
        }
    }


    @GetMapping("/api/car")
    public ResponseEntity<ApiResponse<List<Car>>> fetchAllCars() {
        try {
            List<Car> cars = carService.fetchAll();
            return ResponseEntity.ok(ApiResponse.success(Messages.CARS_GET, cars));
        } catch (CarNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(Messages.CAR_NOT_FOUND,null));
        }
    }
    @GetMapping("/api/car/{id}")
    public ResponseEntity<ApiResponse<Car>> fetchById(@PathVariable("id") Long id) {
        try {
            Car car = carService.fetchById(id);
            return ResponseEntity.ok(ApiResponse.success(Messages.SUCCESS, car));
        } catch (CarNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(Messages.CAR_NOT_FOUND, null));
        }
    }

    @PutMapping("/api/car/{id}")
    public ResponseEntity<ApiResponse<Car>> updateCar(@PathVariable("id") Long id, @RequestBody Car car) {
        try {
            Car updatedCar = carService.updateCar(id, car);
            return ResponseEntity.ok(ApiResponse.success(Messages.CAR_UPDATED, updatedCar));
        } catch (CarNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(Messages.CAR_NOT_FOUND, null));
        }
    }

    @GetMapping("/api/car/name/{name}")
    public ResponseEntity<ApiResponse<List<Car>>> fetchByName(@PathVariable("name") String name) {
        try {
            List<Car> cars = carService.fetchByName(name);
            return ResponseEntity.ok(ApiResponse.success(Messages.SUCCESS, cars));
        } catch (CarNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(Messages.CAR_NOT_FOUND, null));
        }
    }

    @GetMapping("/api/car/made/{made}")
    public ResponseEntity<ApiResponse<List<Car>>> fetchByMade(@PathVariable("made") String made) {
        try {
            List<Car> cars = carService.fetchByMade(made);
            return ResponseEntity.ok(ApiResponse.success(Messages.SUCCESS, cars));
        } catch (CarNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(Messages.CAR_NOT_FOUND, null));
        }
    }

    @GetMapping("/api/car/{made}/{name}/{year}")
    public ResponseEntity<ApiResponse<List<Car>>> fetchByMadeNameYear(
            @PathVariable("made") String made,
            @PathVariable("name") String name,
            @PathVariable("year") String year) {
        try {
            List<Car> cars = carService.fetchByMadeNameYear(made, name, year);
            return ResponseEntity.ok(ApiResponse.success(Messages.SUCCESS, cars));
        } catch (CarNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(Messages.CAR_NOT_FOUND, null));
        }
    }

    @DeleteMapping("/api/car/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCar(@PathVariable("id") Long id) {
        try {
            carService.deleteCar(id);
            return ResponseEntity.ok(ApiResponse.success(Messages.CAR_DELETED, null));
        } catch (CarNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(Messages.CAR_NOT_FOUND, null));
        }
    }
}
