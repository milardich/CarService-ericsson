package com.ericsson.sm.CarApp.service.mapper;

import com.ericsson.sm.CarApp.dto.CarServiceRequestDto;
import com.ericsson.sm.CarApp.model.Car;
import com.ericsson.sm.CarApp.model.CarService;
import com.ericsson.sm.CarApp.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class CarServiceDtoMapper {
    private final CarRepository carRepository;

    public CarService toEntity(Long carId, CarServiceRequestDto dto){
        CarService carService = new CarService();
        Car car = carRepository.findById(carId).orElseThrow(
                () -> new EntityNotFoundException("Car with id " + carId + " not found")
        );
        carService.setCar(car);
        carService.setDateOfService(LocalDateTime.now());
        carService.setWorkerFirstName(dto.getWorkerFirstName());
        carService.setWorkerLastName(dto.getWorkerLastName());
        carService.setWorkDescription(dto.getWorkDescription());
        carService.setPrice(dto.getPrice());
        carService.setPaid(dto.isPaid());

        return carService;
    }
}
