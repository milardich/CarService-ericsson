package com.ericsson.sm.CarApp.controllers;

import com.ericsson.sm.CarApp.dto.CarResponseDto;
import com.ericsson.sm.CarApp.model.Car;
import com.ericsson.sm.CarApp.model.enumeration.CarType;
import com.ericsson.sm.CarApp.service.impl.CarServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.RequestResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.id.IdentifierGeneratorHelper.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarServiceImpl carService;

    @Test
    void getAllCarsOkTest() throws Exception {

        CarResponseDto carDto = new CarResponseDto();
        carDto.setCarType(CarType.BMW_3);
        carDto.setRegistrationMark("WW 123 EE");
        carDto.setManufactureYear(2003);
        carDto.setColor("red");
        carDto.setCarServices(new ArrayList<>());

        List<CarResponseDto> cars = List.of(carDto);

        Mockito.when(carService.getAll()).thenReturn(cars);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    void getCarByIdOkTest() throws Exception {

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
}
