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
import com.ericsson.sm.CarApp.validation.CarValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        if(!clientRepository.existsById(id)){
            throw new EntityNotFoundException("Client with id " + id + " not found");
        }
        Client client = clientRepository.getReferenceById(id);
        CarValidation carValidation = new CarValidation();
        carValidation.validate(carRequestDto);
        Car car = carDtoMapper.toEntity(carRequestDto);

        List<com.ericsson.sm.CarApp.model.CarService> carServices = new ArrayList<>();
        car.setCarServices(carServices);

        car.setClient(client);
        carRepository.save(car);

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
        if(!carRepository.existsById(id)){
            throw new EntityNotFoundException("Car with id " + id + " not found");
        }
        Car car = carRepository.getReferenceById(id);
        return carDtoMapper.toDto(car);
    }

    @Override
    public ResponseEntity<String> deleteById(Long clientId, Long carId) {
        if(!clientRepository.existsById(clientId)){
            throw new EntityNotFoundException("Client with id " + clientId + " not found");
        }
        if(!carRepository.existsById(carId)){
            throw new EntityNotFoundException("Car with id " + carId + " not found");
        }

        carRepository.deleteById(carId);

        return new ResponseEntity<>("Car deleted", HttpStatus.OK);
    }

    @Override
    public CarResponseDto updateById(Long clientId, Long carId, CarRequestDto carRequestDto) {
        if(!clientRepository.existsById(clientId)){
            throw new EntityNotFoundException("Client with id " + clientId + " not found");
        }
        if(!carRepository.existsById(carId)){
            throw new EntityNotFoundException("Car with id " + carId + " not found");
        }

        Car car = carDtoMapper.toEntity(carId, carRequestDto);
        Car savedCar = carRepository.save(car);

        return carDtoMapper.toDto(savedCar);
    }
}
