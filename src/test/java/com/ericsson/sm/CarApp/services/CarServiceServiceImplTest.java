package com.ericsson.sm.CarApp.services;

import com.ericsson.sm.CarApp.dto.CarResponseDto;
import com.ericsson.sm.CarApp.dto.CarServiceRequestDto;
import com.ericsson.sm.CarApp.dto.CarServiceResponseDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Car;
import com.ericsson.sm.CarApp.model.CarService;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.CarRepository;
import com.ericsson.sm.CarApp.repository.CarServiceRepository;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import com.ericsson.sm.CarApp.service.EmailService;
import com.ericsson.sm.CarApp.service.impl.CarServiceServiceImpl;
import com.ericsson.sm.CarApp.service.mapper.CarServiceMapper;
import com.ericsson.sm.CarApp.service.mapper.ClientMapper;
import com.ericsson.sm.CarApp.validation.CarServiceValidation;
import com.ericsson.sm.CarApp.validation.CarValidation;
import com.ericsson.sm.CarApp.validation.ClientValidation;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class CarServiceServiceImplTest {

    @Mock
    private  CarServiceRepository carServiceRepository;
    @Mock
    private  ClientRepository clientRepository;
    @Mock
    private  CarRepository carRepository;
    @Mock
    private  CarValidation carValidation;
    @Mock
    private  ClientValidation clientValidation;
    @Mock
    private  CarServiceValidation carServiceValidation;
    @Mock
    private  EmailService emailService;
    @Mock
    private  CarServiceMapper carServiceMapper;
    @Mock
    private  ClientMapper clientMapper;

    @InjectMocks
    private CarServiceServiceImpl carServiceService;


    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testSaveCarService_returnClientResponse() {

        CarServiceRequestDto expectedCarServiceDto = new CarServiceRequestDto();
        expectedCarServiceDto.setDateOfService(LocalDateTime.now());
        expectedCarServiceDto.setPaid(true);
        expectedCarServiceDto.setPrice(999.2F);
        expectedCarServiceDto.setWorkDescription("Ulje");
        expectedCarServiceDto.setWorkerFirstName("Waltuh");
        expectedCarServiceDto.setWorkerLastName("Jesse");

        CarServiceResponseDto carServiceResponseDto = new CarServiceResponseDto();
        carServiceResponseDto.setDateOfService(expectedCarServiceDto.getDateOfService());
        carServiceResponseDto.setPaid(true);
        carServiceResponseDto.setPrice(999.2F);
        carServiceResponseDto.setWorkDescription("Ulje");
        carServiceResponseDto.setWorkerFirstName("Waltuh");
        carServiceResponseDto.setWorkerLastName("Jesse");

        CarResponseDto carResponseDto = new CarResponseDto();
        carResponseDto.setRegistrationMark("RR 222 RR");
        carResponseDto.setCarServices(new ArrayList<>());
        carResponseDto.getCarServices().add(carServiceResponseDto);

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setCars(new ArrayList<>());
        clientResponseDto.getCars().add(carResponseDto);

        CarService carService = new CarService();
        carService.setCarServiceId(55L);
        carService.setPrice(999.2F);
        carService.setWorkerFirstName("Waltuh");
        carService.setWorkerLastName("Jesse");
        carService.setWorkDescription("Ulje");
        carService.setPaid(true);

        Car car = new Car();
        car.setId(55L);
        car.setRegistrationMark("RR 222 RR");
        car.setCarServices(new ArrayList<>());
        car.getCarServices().add(carService);

        Client client = new Client();
        client.setFirstName("Klijenat");
        client.setCars(new ArrayList<>());
        client.getCars().add(car);

        Mockito.doNothing().when(clientValidation).existsById(Mockito.anyLong());
        Mockito.doNothing().when(carValidation).existsById(Mockito.anyLong());
        Mockito.when(clientRepository.getReferenceById(Mockito.anyLong())).thenReturn(client);
        Mockito.when(carRepository.getReferenceById(Mockito.anyLong())).thenReturn(car);
        Mockito.when(carServiceMapper.toEntity(Mockito.any(CarServiceRequestDto.class))).thenReturn(carService);
        Mockito.when(carServiceRepository.save(Mockito.any(CarService.class))).thenReturn(carService);
        Mockito.when(clientMapper.toDto(Mockito.any(Client.class))).thenReturn(clientResponseDto);

        ClientResponseDto actualSavedData = carServiceService.save(55L, 55L, expectedCarServiceDto);

        Assertions.assertEquals(expectedCarServiceDto.getWorkDescription(), actualSavedData.getCars().get(0).getCarServices().get(0).getWorkDescription());

    }
}
