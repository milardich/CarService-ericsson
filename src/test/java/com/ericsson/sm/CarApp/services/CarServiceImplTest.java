package com.ericsson.sm.CarApp.services;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.CarResponseDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Car;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.repository.CarRepository;
import com.ericsson.sm.CarApp.repository.ClientRepository;
import com.ericsson.sm.CarApp.service.impl.CarServiceImpl;
import com.ericsson.sm.CarApp.service.mapper.CarMapper;
import com.ericsson.sm.CarApp.service.mapper.ClientMapper;
import com.ericsson.sm.CarApp.validation.CarValidation;
import com.ericsson.sm.CarApp.validation.ClientValidation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarValidation carValidation;

    @Mock
    private CarMapper carMapper;

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private ClientValidation clientValidation;

    @Mock
    private ClientRepository clientRepository;


    @InjectMocks
    private CarServiceImpl carService;



    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testSaveCar_returnsOkWithClientResponse() {

        CarRequestDto expectedCarRequestDto = new CarRequestDto();
        expectedCarRequestDto.setCarType("BMW_3");
        expectedCarRequestDto.setColor("Red");
        expectedCarRequestDto.setManufactureYear(2009);
        expectedCarRequestDto.setRegistrationMark("ww 222 ww");

        CarResponseDto carResponseDto = new CarResponseDto();
        carResponseDto.setRegistrationMark("ww 222 ww");

        List<CarResponseDto> carDtoList = new ArrayList<>();
        carDtoList.add(carResponseDto);

        Car car = new Car();
        car.setId(1L);
        car.setRegistrationMark("ww 222 ww");
        List<Car> cars = new ArrayList<>();
        cars.add(car);

        Client client = new Client();
        client.setFirstName("test1");
        client.setCars(cars);

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setFirstName(client.getFirstName());
        clientResponseDto.setCars(carDtoList);

        Mockito.doNothing().when(clientValidation).existsById(Mockito.anyLong());
        Mockito.doNothing().when(carValidation).validate(Mockito.any(CarRequestDto.class));
        Mockito.when(clientRepository.getReferenceById(Mockito.anyLong())).thenReturn(client);
        Mockito.when(carMapper.toEntity(Mockito.any(CarRequestDto.class))).thenReturn(car);
        Mockito.when(carRepository.save(Mockito.any(Car.class))).thenReturn(car);
        Mockito.when(clientMapper.toDto(client)).thenReturn(clientResponseDto);

        ClientResponseDto actualSavedClientResponse = carService.save(55L, expectedCarRequestDto);

        Assert.assertEquals(expectedCarRequestDto.getRegistrationMark(), actualSavedClientResponse.getCars().get(0).getRegistrationMark());
    }

}
