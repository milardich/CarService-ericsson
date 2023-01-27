package com.ericsson.sm.CarApp.service.impl;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.CarResponseDto;
import com.ericsson.sm.CarApp.model.Car;
import com.ericsson.sm.CarApp.repository.CarRepository;
import com.ericsson.sm.CarApp.service.CarService;
import com.ericsson.sm.CarApp.service.mapper.CarDtoMapper;
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

    @Override
    public CarResponseDto save(CarRequestDto carRequestDto) {
        Car car = carDtoMapper.toEntity(carRequestDto);
        Car savedCar = carRepository.save(car);
        return carDtoMapper.toDto(savedCar);
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
    public ResponseEntity<String> deleteById(Long id) {
        carRepository.deleteById(id);
        return new ResponseEntity<>("Car deleted", HttpStatus.OK);
    }

    @Override
    public CarResponseDto updateById(Long id) {
        return null;
    }
}
