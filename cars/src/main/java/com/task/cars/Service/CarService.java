package com.task.cars.Service;

import com.task.cars.Error.CarNotFoundException;
import com.task.cars.Model.Car;

import java.util.List;

public interface CarService {
    public Car saveCar(Car car);

   public List<Car> fetchAll() throws CarNotFoundException;

   public Car updateCar(Long id, Car car) throws CarNotFoundException;

   public List<Car> fetchByName(String name) throws CarNotFoundException;

    public List<Car> fetchByMade(String made) throws CarNotFoundException;


    public List<Car> fetchByMadeNameYear(String made, String name, String year) throws CarNotFoundException;

    public String deleteCar(Long id) throws CarNotFoundException;

    public Car fetchById(Long id) throws CarNotFoundException;
}
