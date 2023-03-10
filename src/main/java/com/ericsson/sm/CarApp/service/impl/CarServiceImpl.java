package com.ericsson.sm.CarApp.service.impl;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.CarResponseDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Car;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.CarRepository;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import com.ericsson.sm.CarApp.service.CarService;
import com.ericsson.sm.CarApp.service.mapper.CarMapper;
import com.ericsson.sm.CarApp.service.mapper.ClientMapper;
import com.ericsson.sm.CarApp.validation.CarValidation;
import com.ericsson.sm.CarApp.validation.ClientValidation;
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
    private final ClientRepository clientRepository;
    private final CarValidation carValidation;
    private final ClientValidation clientValidation;
    private final CarMapper carMapper;
    private final ClientMapper clientMapper;

    @Override
    public ClientResponseDto save(Long id, CarRequestDto carRequestDto) {
        clientValidation.existsById(id);
        carValidation.validate(carRequestDto);

        Client client = clientRepository.getReferenceById(id);

        Car car = carMapper.toEntity(carRequestDto);

        List<com.ericsson.sm.CarApp.model.CarService> carServices = new ArrayList<>();

        car.setCarServices(carServices);
        car.setClient(client);
        carRepository.save(car);

        return clientMapper.toDto(client);
    }

    @Override
    public List<CarResponseDto> getAll() {
        List<Car> cars = carRepository.findAll();
        List<CarResponseDto> savedCars = new ArrayList<>();
        for(Car car : cars){
            CarResponseDto carResponseDto = carMapper.toDto(car);
            savedCars.add(carResponseDto);
        }
        return savedCars;
    }

    @Override
    public CarResponseDto findById(Long id) {
        carValidation.existsById(id);
        Car car = carRepository.getReferenceById(id);
        return carMapper.toDto(car);
    }

    @Override
    public ResponseEntity<String> deleteById(Long clientId, Long carId) {
        clientValidation.existsById(clientId);
        carValidation.existsById(carId);
        carRepository.deleteById(carId);

        return new ResponseEntity<>("Car deleted", HttpStatus.OK);
    }

    @Override
    public CarResponseDto updateById(Long clientId, Long carId, CarRequestDto carRequestDto) {
        clientValidation.existsById(clientId);
        carValidation.existsById(carId);

        Car car = carRepository.getReferenceById(carId);

        car = carMapper.toEntity(car, carRequestDto);

        Car savedCar = carRepository.save(car);

        return carMapper.toDto(savedCar);
    }
}
