package com.ericsson.sm.CarApp.controllers;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.CarResponseDto;

import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Car;
import com.ericsson.sm.CarApp.model.Client;
import com.ericsson.sm.CarApp.model.enumeration.CarType;
import com.ericsson.sm.CarApp.service.impl.CarServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.yaml.snakeyaml.util.EnumUtils;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarServiceImpl carService;

    @Test
    void testGetAllCars_returnOk() throws Exception {

        CarResponseDto carDto = new CarResponseDto();
        carDto.setCarType(CarType.BMW_3);
        carDto.setRegistrationMark("WW 123 EE");
        carDto.setManufactureYear(2003);
        carDto.setColor("red");
        carDto.setCarServices(new ArrayList<>());

        List<CarResponseDto> cars = List.of(carDto, carDto);

        Mockito.when(carService.getAll()).thenReturn(cars);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    void testGetCarById_returnOk() throws Exception {

        CarResponseDto carDto = new CarResponseDto();
        carDto.setCarType(CarType.BMW_3);
        carDto.setRegistrationMark("WW 123 EE");
        carDto.setManufactureYear(2003);
        carDto.setColor("red");
        carDto.setCarServices(new ArrayList<>());

        Mockito.when(carService.findById(34L)).thenReturn(carDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars/34").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    void testSaveCar_returnClientResponse() throws Exception{

        ObjectMapper objectMapper = new ObjectMapper();

        CarRequestDto carRequestDto = new CarRequestDto();
        carRequestDto.setRegistrationMark("TE 577 ES");
        carRequestDto.setColor("TEST");
        carRequestDto.setManufactureYear(9999);
        carRequestDto.setCarType("OPEL_ASTRA");

        Client client = new Client();
        client.setFirstName("TEST_CLIENT");
        client.setId(55L);

        Car car = new Car();
        car.setRegistrationMark(carRequestDto.getRegistrationMark());
        car.setCarType(CarType.OPEL_ASTRA);
        car.setManufactureYear(carRequestDto.getManufactureYear());
        car.setColor(carRequestDto.getColor());
        car.setClient(client);

        CarResponseDto carResponseDto = new CarResponseDto();
        carResponseDto.setCarType(car.getCarType());
        carResponseDto.setManufactureYear(car.getManufactureYear());
        carResponseDto.setRegistrationMark(car.getRegistrationMark());
        carResponseDto.setColor(car.getColor());
        carResponseDto.setCarServices(new ArrayList<>());

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setFirstName(client.getFirstName());
        clientResponseDto.setCars(new ArrayList<>());
        clientResponseDto.getCars().add(carResponseDto);

        Mockito.when(carService.save(Mockito.anyLong(), Mockito.any(CarRequestDto.class))).thenReturn(clientResponseDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers/55/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(carRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cars[*].registrationMark")
                        .value(carRequestDto.getRegistrationMark()))
                .andDo(print());
    }
}
