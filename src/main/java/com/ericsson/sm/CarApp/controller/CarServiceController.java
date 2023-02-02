package com.ericsson.sm.CarApp.controller;

import com.ericsson.sm.CarApp.dto.CarServiceRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.CarService;
import com.ericsson.sm.CarApp.repository.CarServiceRepository;
import com.ericsson.sm.CarApp.service.CarServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class CarServiceController {

    private final CarServiceService carServiceService;
    private final CarServiceRepository carServiceRepository;

    @PostMapping("/api/customers/{clientId}/cars/{carId}/car-services")
    public ClientResponseDto save(@PathVariable Long clientId,
                                  @PathVariable Long carId,
                                  @RequestBody CarServiceRequestDto carServiceRequestDto
    ) {
        return carServiceService.save(clientId, carId, carServiceRequestDto);
    }
//
//    @GetMapping("/api/test")
//    public ClientResponseDto test(){
//        ClientResponseDto clientResponseDto = new ClientResponseDto();
//        CarService carServiceTest = new CarService();
//        carServiceTest.setDateOfService(LocalDateTime.now());
//        carServiceTest.setWorkerFirstName("testni workar");
//        carServiceTest.setPrice(12.41F);
//        carServiceTest.setPaid(true);
//        carServiceRepository.save(carServiceTest);
//        return clientResponseDto;
//    }
}
