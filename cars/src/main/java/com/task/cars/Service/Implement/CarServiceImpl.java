package com.task.cars.Service.Implement;

import com.task.cars.Constants.Status;
import com.task.cars.Error.CarNotFoundException;
import com.task.cars.Model.Car;
import com.task.cars.Repository.CarRepo;
import com.task.cars.Service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.task.cars.Constants.Status.DELETED;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepo carRepository;
    private Object Car;
    private String name;

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> fetchAll() throws CarNotFoundException {
        List<Car> cars = carRepository.findAllByStatusNot(Status.valueOf("DELETED"));
        if (cars.isEmpty()) {
            throw new CarNotFoundException("No cars in the garage");
        }
        return cars;
    }
    @Override
    public com.task.cars.Model.Car fetchById(Long id) throws CarNotFoundException {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car Not Found"));
        if (DELETED.equals(car.getStatus())) {
            throw new CarNotFoundException("Car not found");
        }
        return car;
    }
    @Override
    public Car updateCar(Long id, Car car) throws CarNotFoundException {
//        return ;
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Received request to update car with ID {}: {}", id, car);
        try {
            Car carDB = carRepository.findById(id)
                    .orElseThrow(() -> new CarNotFoundException("Car not found"));

            carDB.setCarName(Objects.nonNull(car.getCarName()) && !"".equalsIgnoreCase(car.getCarName()) ?
                    car.getCarName() : carDB.getCarName());

            carDB.setModel(Objects.nonNull(car.getModel()) && !"".equalsIgnoreCase(car.getModel()) ?
                    car.getModel() : carDB.getModel());

            carDB.setYear(Objects.nonNull(car.getYear()) && car.getYear().describeConstable().isPresent() ?
                    car.getYear() : carDB.getYear());

            carDB.setDescription(Objects.nonNull(car.getDescription()) && !"".equalsIgnoreCase(car.getDescription()) ?
                    car.getDescription() : carDB.getDescription());

            carDB.setMade(Objects.nonNull(car.getMade()) && !"".equalsIgnoreCase(car.getMade()) ?
                    car.getMade() : carDB.getMade());

            carDB.setStatus(Objects.nonNull(car.getStatus()) ? car.getStatus() : carDB.getStatus());

            return carRepository.save(carDB);

        } catch (RuntimeException | CarNotFoundException e) {
            throw new CarNotFoundException(e);
        }

    }

    @Override
    public List<Car> fetchByName(String name) throws CarNotFoundException {
        List<Car> cars = carRepository.findAllByCarNameContainingIgnoreCase(name);
        if (cars.isEmpty()) {
            throw new CarNotFoundException("No cars found for the specified criteria");
        }
        return cars;
    }

    @Override
    public List<Car> fetchByMade(String made) throws CarNotFoundException {
       List <Car> cars = carRepository.findAllByMadeIgnoreCase(made);
        if (cars.isEmpty()) {
            throw new CarNotFoundException("No cars found for the specified criteria");
        }
        return cars;
    }

    @Override
    public List<Car> fetchByMadeNameYear(String made, String name, String year) throws CarNotFoundException {
        List<Car> cars = carRepository.findByNameMadeYear(made, name, year);
        if (cars.isEmpty()) {
            throw new CarNotFoundException("No cars found for the specified criteria");
        }
        return cars;
    }

    @Override
    public String deleteCar(Long id) throws CarNotFoundException {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found"));
        car.setStatus(DELETED);
        carRepository.save(car);
        return "deleted";
    }

}
