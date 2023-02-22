package com.ericsson.sm.CarApp.controllers;

import com.ericsson.sm.CarApp.dto.*;
import com.ericsson.sm.CarApp.service.impl.CarServiceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class CarServiceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarServiceServiceImpl carServiceService;

    @Test
    void testSaveCarService_returnOk() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();

        Mockito.when(carServiceService.save(Mockito.anyLong(), Mockito.anyLong(), Mockito.any(CarServiceRequestDto.class)))
                .thenReturn(new ClientResponseDto());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers/55/cars/34/car-services")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new CarServiceRequestDto())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());

    }

    @Test
    void testDeleteCarServiceById_returnOk() throws Exception {
        Mockito.when(carServiceService.deleteById(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(ResponseEntity.ok("Car service deleted"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/customers/55/cars/66/car-services/77")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testUpdateCarServiceById_returnOk() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Mockito.when(carServiceService.updateById(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong(), Mockito.any(CarServiceRequestDto.class)))
                .thenReturn(new CarServiceResponseDto());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/customers/55/cars/66/car-services/77")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new CarServiceRequestDto())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    void testUpdateIsPaid_returnOk() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Mockito.when(carServiceService.updateIsPaid(Mockito.anyLong(),
                    Mockito.anyLong(),
                    Mockito.anyLong(),
                    Mockito.any(CarServiceIsPaidRequestDto.class)))
                .thenReturn(new CarServiceIsPaidResponseDto());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/customers/44/cars/55/car-services/66/is-paid")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new CarServiceIsPaidRequestDto())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }
}
