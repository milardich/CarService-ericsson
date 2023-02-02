package com.ericsson.sm.CarApp.service.impl;

import com.ericsson.sm.CarApp.dto.CarServiceRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Car;
import com.ericsson.sm.CarApp.model.CarService;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.CarRepository;
import com.ericsson.sm.CarApp.repository.CarServiceRepository;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import com.ericsson.sm.CarApp.service.CarServiceService;
import com.ericsson.sm.CarApp.service.mapper.CarServiceDtoMapper;
import com.ericsson.sm.CarApp.service.mapper.ClientDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CarServiceServiceImpl implements CarServiceService {
    private final CarServiceRepository carServiceRepository;
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;
    private final ClientDtoMapper clientDtoMapper;
    private final CarServiceDtoMapper carServiceDtoMapper;

    @Override
    public ClientResponseDto save(Long clientId, Long carId, CarServiceRequestDto carServiceRequestDto) {
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new EntityNotFoundException("Client with id " + clientId + " not found")
        );

        Car car = carRepository.findById(carId).orElseThrow(
                () -> new EntityNotFoundException("Car with id " + carId + " not found")
        );

        CarService carService = carServiceDtoMapper.toEntity(carId, carServiceRequestDto);

        car.getCarServices().add(carService);

        carServiceRepository.save(carService);

        return clientDtoMapper.toDto(client);
    }
}
