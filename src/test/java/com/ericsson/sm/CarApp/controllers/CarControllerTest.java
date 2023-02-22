package com.ericsson.sm.CarApp.controllers;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.CarResponseDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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
    void testSaveCar_returnOk() throws Exception{

        ObjectMapper objectMapper = new ObjectMapper();

        CarRequestDto carRequestDto = new CarRequestDto();
        carRequestDto.setRegistrationMark("TE 577 ES");
        carRequestDto.setColor("TEST");
        carRequestDto.setManufactureYear(9999);
        carRequestDto.setCarType("OPEL_ASTRA");

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setFirstName("TEST");
        clientResponseDto.setCars(new ArrayList<>());
        clientResponseDto.getCars().add(new CarResponseDto());

        Mockito.when(carService.save(Mockito.anyLong(), Mockito.any(CarRequestDto.class))).thenReturn(clientResponseDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers/55/cars")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    void testDeleteCarById_returnOk() throws Exception {

        Mockito.when(carService.deleteById(Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(ResponseEntity.ok("User deleted"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/customers/88/cars/26")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    void testUpdateCarById_returnOk() throws Exception{

        ObjectMapper objectMapper = new ObjectMapper();

        CarRequestDto carRequestDto = new CarRequestDto();
        carRequestDto.setCarType("OPEL_ASTRA");
        carRequestDto.setColor("Crveni");
        carRequestDto.setRegistrationMark("HE 189 HE");
        carRequestDto.setManufactureYear(2081);


        CarResponseDto carResponseDto = new CarResponseDto();
        carResponseDto.setCarType(CarType.OPEL_ASTRA);
        carResponseDto.setColor(carRequestDto.getColor());
        carResponseDto.setRegistrationMark(carRequestDto.getRegistrationMark());
        carResponseDto.setManufactureYear(carRequestDto.getManufactureYear());
        carResponseDto.setCarServices(new ArrayList<>());

        Mockito.when(carService.updateById(Mockito.anyLong(), Mockito.anyLong(), Mockito.any(CarRequestDto.class)))
                .thenReturn(carResponseDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/customers/44/cars/26")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }
}
