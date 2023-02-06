package com.ericsson.sm.CarApp.service.mapper;

import com.ericsson.sm.CarApp.dto.CarServiceRequestDto;
import com.ericsson.sm.CarApp.dto.CarServiceResponseDto;
import com.ericsson.sm.CarApp.model.Car;
import com.ericsson.sm.CarApp.model.CarService;
import com.ericsson.sm.CarApp.repository.CarRepository;
import com.ericsson.sm.CarApp.repository.CarServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;


@Service
@RequiredArgsConstructor
public class CarServiceDtoMapper {
    private final CarRepository carRepository;
    private final CarServiceRepository carServiceRepository;

    public CarService toEntity(Long carId, Long carServiceId, CarServiceRequestDto dto){
        if(!carServiceRepository.existsById(carServiceId)){
            throw new EntityNotFoundException("CarService with id " + carServiceId + " not found");
        }
        if(!carRepository.existsById(carId)){
            throw new EntityNotFoundException("Car with id " + carId + " not found");
        }

        CarService carService = carServiceRepository.getReferenceById(carServiceId);
        Car car = carRepository.getReferenceById(carId);

        carService.setCar(car);
        carService.setDateOfService(dto.getDateOfService());
        carService.setWorkerFirstName(dto.getWorkerFirstName());
        carService.setWorkerLastName(dto.getWorkerLastName());
        carService.setWorkDescription(dto.getWorkDescription());
        carService.setPrice(dto.getPrice());
        carService.setPaid(dto.isPaid());

        return carService;
    }


    public CarService toEntity(Long carId, CarServiceRequestDto dto){
        CarService carService = new CarService();

        if(!carRepository.existsById(carId)){
            throw new EntityNotFoundException("Car with id " + carId + " not found");
        }

        Car car = carRepository.getReferenceById(carId);

        carService.setCar(car);
        carService.setDateOfService(dto.getDateOfService());
        carService.setWorkerFirstName(dto.getWorkerFirstName());
        carService.setWorkerLastName(dto.getWorkerLastName());
        carService.setWorkDescription(dto.getWorkDescription());
        carService.setPrice(dto.getPrice());
        carService.setPaid(dto.isPaid());

        return carService;
    }



    public CarServiceResponseDto toDto(CarService carService){
        CarServiceResponseDto carServiceResponseDto = new CarServiceResponseDto();

        carServiceResponseDto.setDateOfService(carService.getDateOfService());
        carServiceResponseDto.setWorkerFirstName(carService.getWorkerFirstName());
        carServiceResponseDto.setWorkerLastName(carService.getWorkerLastName());
        carServiceResponseDto.setWorkDescription(carService.getWorkDescription());
        carServiceResponseDto.setPrice(carService.getPrice());
        carServiceResponseDto.setPaid(carService.isPaid());

        return carServiceResponseDto;
    }
}
