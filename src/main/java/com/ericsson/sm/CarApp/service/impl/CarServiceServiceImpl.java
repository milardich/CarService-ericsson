package com.ericsson.sm.CarApp.service.impl;

import com.ericsson.sm.CarApp.dto.*;
import com.ericsson.sm.CarApp.model.Car;
import com.ericsson.sm.CarApp.model.CarService;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.CarRepository;
import com.ericsson.sm.CarApp.repository.CarServiceRepository;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import com.ericsson.sm.CarApp.service.CarServiceService;
import com.ericsson.sm.CarApp.service.mapper.CarServiceDtoMapper;
import com.ericsson.sm.CarApp.service.mapper.ClientDtoMapper;
import com.ericsson.sm.CarApp.validation.CarServiceValidation;
import com.ericsson.sm.CarApp.validation.CarValidation;
import com.ericsson.sm.CarApp.validation.ClientValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class CarServiceServiceImpl implements CarServiceService {
    private final CarServiceRepository carServiceRepository;
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;
    private final ClientDtoMapper clientDtoMapper;
    private final CarServiceDtoMapper carServiceDtoMapper;
    private final CarValidation carValidation;
    private final ClientValidation clientValidation;
    private final CarServiceValidation carServiceValidation;

    @Override
    public ClientResponseDto save(Long clientId, Long carId, CarServiceRequestDto carServiceRequestDto) {
        clientValidation.existsById(clientId);
        carValidation.existsById(carId);

        Client client = clientRepository.getReferenceById(clientId);
        Car car = carRepository.getReferenceById(carId);

        if(!client.getCars().contains(car)){
            throw new EntityNotFoundException("Client with id " + clientId + " does not own that car");
        }

        CarService carService = carServiceDtoMapper.toEntity(carId, carServiceRequestDto);

        carServiceRepository.save(carService);

        return clientDtoMapper.toDto(client);
    }


    @Override
    public ResponseEntity<String> deleteById(Long clientId, Long carId, Long carServiceId) {
        clientValidation.existsById(clientId);
        carValidation.existsById(carId);
        carServiceValidation.existsById(carServiceId);

        Client client = clientRepository.getReferenceById(clientId);
        Car car = carRepository.getReferenceById(carId);
        CarService carService = carServiceRepository.getReferenceById(carServiceId);

        if(!client.getCars().contains(car)){
            throw new EntityNotFoundException("Client does not own that car");
        }

        if(!car.getCarServices().contains(carService)){
            throw new EntityNotFoundException("Car does not have that carService");
        }

        carServiceRepository.deleteById(carServiceId);

        return new ResponseEntity<>("Car service deleted", HttpStatus.OK);
    }


    @Override
    public CarServiceResponseDto updateById(Long clientId, Long carId, Long carServiceId, CarServiceRequestDto carServiceRequestDto) {
        clientValidation.existsById(clientId);
        carValidation.existsById(carId);
        carServiceValidation.existsById(carServiceId);

        Client client = clientRepository.getReferenceById(clientId);
        Car car = carRepository.getReferenceById(carId);
        CarService carService = carServiceRepository.getReferenceById(carServiceId);

        if(!client.getCars().contains(car)){
            throw new EntityNotFoundException("Client does not own that car");
        }

        if(!car.getCarServices().contains(carService)){
            throw new EntityNotFoundException("Car does not have that carService");
        }

        CarService updatedCarService = carServiceDtoMapper.toEntity(carId, carServiceId, carServiceRequestDto);
        CarService savedCarService = carServiceRepository.save(updatedCarService);

        return carServiceDtoMapper.toDto(savedCarService);
    }

    @Override
    public CarServiceIsPaidResponseDto updateIsPaid(Long clientId, Long carId, Long carServiceId, CarServiceIsPaidRequestDto carServiceIsPaidRequestDto) {
        CarServiceIsPaidResponseDto response = new CarServiceIsPaidResponseDto();
        clientValidation.existsById(clientId);
        carValidation.existsById(carId);
        carServiceValidation.existsById(carServiceId);

        Client client = clientRepository.getReferenceById(clientId);
        Car car = carRepository.getReferenceById(carId);
        CarService carService = carServiceRepository.getReferenceById(carServiceId);

        if(!client.getCars().contains(car)){
            throw new EntityNotFoundException("Client does not own that car");
        }
        if(!car.getCarServices().contains(carService)){
            throw new EntityNotFoundException("Car does not have that Car Service");
        }

        carService.setPaid(carServiceIsPaidRequestDto.isPaid());
        carServiceRepository.save(carService);

        response.setMessage("Success");
        return response;
    }
}
