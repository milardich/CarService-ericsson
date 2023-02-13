package com.ericsson.sm.CarApp.service.impl;

import com.ericsson.sm.CarApp.dto.*;
import com.ericsson.sm.CarApp.model.Car;
import com.ericsson.sm.CarApp.model.CarService;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.CarRepository;
import com.ericsson.sm.CarApp.repository.CarServiceRepository;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import com.ericsson.sm.CarApp.service.CarServiceService;
import com.ericsson.sm.CarApp.service.EmailService;
import com.ericsson.sm.CarApp.service.mapper.CarServiceDtoMapper;
import com.ericsson.sm.CarApp.service.mapper.CarServiceMapper;
import com.ericsson.sm.CarApp.service.mapper.ClientDtoMapper;
import com.ericsson.sm.CarApp.service.mapper.ClientMapper;
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
    private final EmailService emailService;

    @Override
    public ClientResponseDto save(Long clientId, Long carId, CarServiceRequestDto carServiceRequestDto) {
        clientValidation.existsById(clientId);
        carValidation.existsById(carId);

        Client client = clientRepository.getReferenceById(clientId);
        Car car = carRepository.getReferenceById(carId);

        if(!client.getCars().contains(car)){
            throw new EntityNotFoundException("Client with id " + clientId + " does not own that car");
        }

        CarService carService = CarServiceMapper.INSTANCE.toEntity(carServiceRequestDto);
        carService.setCar(car);

        carServiceRepository.save(carService);

        // send email
        String emailSubject = "New Car Service is added to your car";
        String emailText = "" +
                "Dear " + client.getFirstName() + ", \n\n" +
                "New Car Service has been added to your car (" + car.getRegistrationMark() + ")" +
                "\n\n Lp, Your CarService.";

        emailService.send(client.getEmail(), emailSubject, emailText);

        return ClientMapper.INSTANCE.toDto(client);
    }


    @Override
    public ResponseEntity<String> deleteById(Long clientId, Long carId, Long carServiceId) {
        clientValidation.existsById(clientId);
        carValidation.existsById(carId);
        carServiceValidation.existsById(carServiceId);

        Client client = clientRepository.getReferenceById(clientId);
        Car car = carRepository.getReferenceById(carId);
        CarService carService = carServiceRepository.getReferenceById(carServiceId);

        clientValidation.checkIfClientOwnsCarAndCarHasCarService(client, car, carService);

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

        clientValidation.checkIfClientOwnsCarAndCarHasCarService(client, car, carService);

        CarService updatedCarService = CarServiceMapper.INSTANCE.toEntity(carService, carServiceRequestDto);

        CarService savedCarService = carServiceRepository.save(updatedCarService);

        // send email
        String emailSubject = "Your Car Service has been updated";
        String emailText = "" +
                "Dear " + client.getFirstName() + ", \n\n" +
                "Car Service on your car (" + car.getRegistrationMark() + ") has been updated!" +
                "\n\n Lp, Your CarService.";

        emailService.send(client.getEmail(), emailSubject, emailText);

        return CarServiceMapper.INSTANCE.toDto(savedCarService);
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

        clientValidation.checkIfClientOwnsCarAndCarHasCarService(client, car, carService);

        carService.setPaid(carServiceIsPaidRequestDto.isPaid());
        carServiceRepository.save(carService);

        response.setMessage("Success");
        return response;
    }
}
