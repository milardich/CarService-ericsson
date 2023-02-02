package com.ericsson.sm.CarApp.service.mapper;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.CarResponseDto;
import com.ericsson.sm.CarApp.dto.CarServiceResponseDto;
import com.ericsson.sm.CarApp.model.Car;
import com.ericsson.sm.CarApp.model.CarService;
import com.ericsson.sm.CarApp.model.enumeration.CarType;
import com.ericsson.sm.CarApp.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarDtoMapper {
    private final CarRepository carRepository;

    public CarResponseDto toDto(Car car){
        CarResponseDto carResponseDto = new CarResponseDto();

        carResponseDto.setCarType(car.getCarType());
        carResponseDto.setColor(car.getColor());
        carResponseDto.setManufactureYear(car.getManufactureYear());
        carResponseDto.setRegistrationMark(car.getRegistrationMark());

        // list of carServices to dto carServices
        List<CarServiceResponseDto> cars = new ArrayList<>();
        for(CarService carService : car.getCarServices()){
            CarServiceResponseDto carServiceResponseDto = new CarServiceResponseDto();
            carServiceResponseDto.setDateOfService(carService.getDateOfService());
            carServiceResponseDto.setWorkerFirstName(carService.getWorkerFirstName());
            carServiceResponseDto.setWorkerLastName(carService.getWorkerLastName());
            carServiceResponseDto.setWorkDescription(carService.getWorkDescription());
            carServiceResponseDto.setPrice(carService.getPrice());
            carServiceResponseDto.setPaid(carService.isPaid());
            cars.add(carServiceResponseDto);
        }

        carResponseDto.setCarServices(cars);

        return carResponseDto;
    }

    public Car toEntity(CarRequestDto dto){
        Car car = new Car();

        CarType carType = CarType.valueOf(dto.getCarType());
        car.setCarType(carType);

        car.setColor(dto.getColor());
        car.setManufactureYear(dto.getManufactureYear());
        car.setRegistrationMark(dto.getRegistrationMark());

        return car;
    }

    public Car toEntity(Long id, CarRequestDto dto){
        Car car = carRepository.findById(id).orElse(null);

        CarType carType = CarType.valueOf(dto.getCarType());
        car.setCarType(carType);

        car.setColor(dto.getColor());
        car.setManufactureYear(dto.getManufactureYear());
        car.setRegistrationMark(dto.getRegistrationMark());

        return car;
    }
}
