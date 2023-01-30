package com.ericsson.sm.CarApp.repository;

import com.ericsson.sm.CarApp.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
    
}
