package com.ericsson.sm.CarApp.service.impl;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.CarResponseDto;
import com.ericsson.sm.CarApp.model.Car;
import com.ericsson.sm.CarApp.repository.CarRepository;
import com.ericsson.sm.CarApp.service.CarService;
import com.ericsson.sm.CarApp.service.mapper.CarDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public List<CarRequestDto> getAll() {
        return null;
    }

    @Override
    public CarRequestDto findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        return null;
    }

    @Override
    public CarRequestDto updateById(Long id) {
        return null;
    }
}
