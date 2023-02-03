package com.ericsson.sm.CarApp.repository;

import com.ericsson.sm.CarApp.model.CarService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarServiceRepository extends JpaRepository<CarService, Long> {
}
