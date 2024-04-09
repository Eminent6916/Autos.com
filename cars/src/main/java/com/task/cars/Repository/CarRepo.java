package com.task.cars.Repository;

import com.task.cars.Constants.Status;
import com.task.cars.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarRepo extends JpaRepository<Car, Long> {

    public List<Car> findAllByStatusNot(Status deleted);

//    public List<Car> findAllByStatusNot(String deleted);

    @Query(value = "SELECT * FROM CAR WHERE car_name = :name AND status != 'DELETED'", nativeQuery = true)
            public List <Car> findAllByCarNameWithinIgnoreCaseName(@Param("name")String name);

    @Query(value = "SELECT * FROM CAR WHERE made = :made AND status != 'DELETED'", nativeQuery = true)
    public List<Car> findAllByMadeIgnoreCase(@Param("made") String made);

    @Query(value = "SELECT * FROM CAR WHERE made = :made AND car_name = :name AND year = :year AND status != 'DELETED' ", nativeQuery = true)
    public List<Car> findByNameMadeYear(@Param("made")String made, @Param("name")String name, @Param("year")String year);

    List<Car> findAllByCarNameContainingIgnoreCase(String name);
}


