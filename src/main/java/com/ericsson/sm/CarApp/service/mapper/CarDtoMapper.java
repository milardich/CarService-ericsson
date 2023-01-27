package com.ericsson.sm.CarApp.service.mapper;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.CarResponseDto;
import com.ericsson.sm.CarApp.model.Car;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.CarRepository;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarDtoMapper {
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;

    public CarResponseDto toDto(Car car){
        CarResponseDto carResponseDto = new CarResponseDto();

        carResponseDto.setCarType(car.getCarType());
        carResponseDto.setColor(car.getColor());
        carResponseDto.setClientId(car.getClient().getId());
        carResponseDto.setManufactureYear(car.getManufactureYear());
        carResponseDto.setRegistrationMark(car.getRegistrationMark());

        return carResponseDto;
    }

    public Car toEntity(CarRequestDto dto){
        Car car = new Car();

        car.setCarType(dto.getCarType());
        car.setColor(dto.getColor());

        Client client = clientRepository.findById(dto.getClientId()).orElse(null);
        car.setClient(client);

        car.setManufactureYear(dto.getManufactureYear());
        car.setRegistrationMark(dto.getRegistrationMark());

        return car;
    }

    public Car toEntity(Long id, CarRequestDto dto){
        Car car = carRepository.findById(id).orElse(null);

        car.setCarType(dto.getCarType());
        car.setColor(dto.getColor());

        Client client = clientRepository.findById(dto.getClientId()).orElse(null);
        car.setClient(client);

        car.setManufactureYear(dto.getManufactureYear());
        car.setRegistrationMark(dto.getRegistrationMark());

        return car;
    }
}
