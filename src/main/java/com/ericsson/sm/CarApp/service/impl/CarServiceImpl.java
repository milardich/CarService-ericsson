package com.ericsson.sm.CarApp.service.impl;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.CarResponseDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Car;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.CarRepository;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import com.ericsson.sm.CarApp.service.CarService;
import com.ericsson.sm.CarApp.service.mapper.CarDtoMapper;
import com.ericsson.sm.CarApp.service.mapper.ClientDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarDtoMapper carDtoMapper;
    private final ClientRepository clientRepository;
    private final ClientDtoMapper clientDtoMapper;

    @Override
    public ClientResponseDto save(Long id, CarRequestDto carRequestDto) {
        Client client = clientRepository.findById(id).orElse(null);
        Car car = carDtoMapper.toEntity(carRequestDto);
        car.setClient(client);
        Car savedCar = carRepository.save(car);

        return clientDtoMapper.toDto(client);
    }

    @Override
    public List<CarResponseDto> getAll() {
        List<Car> cars = carRepository.findAll();
        List<CarResponseDto> savedCars = new ArrayList<>();
        for(Car car : cars){
            CarResponseDto carResponseDto = carDtoMapper.toDto(car);
            savedCars.add(carResponseDto);
        }
        return savedCars;
    }

    @Override
    public CarResponseDto findById(Long id) {
        Car car = carRepository.findById(id).orElse(null);
        return carDtoMapper.toDto(car);
    }

    @Override
    public ResponseEntity<String> deleteById(Long clientId, Long carId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        Car car = carRepository.findById(carId).orElse(null);
        client.getCars().remove(car);
        carRepository.deleteById(carId);
        return new ResponseEntity<>("Car deleted", HttpStatus.OK);
    }

    @Override
    public CarResponseDto updateById(Long clientId, Long carId, CarRequestDto carRequestDto) {
        Client client = clientRepository.findById(clientId).orElse(null);
        Car carToRemove = carRepository.findById(carId).orElse(null);
        client.getCars().remove(carToRemove);

        Car car = carDtoMapper.toEntity(carId, carRequestDto);
        Car savedCar = carRepository.save(car);
        client.getCars().add(savedCar);
        return carDtoMapper.toDto(savedCar);
    }
}
