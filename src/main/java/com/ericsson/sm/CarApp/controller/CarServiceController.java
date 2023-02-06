package com.ericsson.sm.CarApp.controller;

import com.ericsson.sm.CarApp.dto.*;
import com.ericsson.sm.CarApp.service.CarServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class CarServiceController {

    private final CarServiceService carServiceService;

    @PostMapping("/api/customers/{clientId}/cars/{carId}/car-services")
    public ClientResponseDto save(@PathVariable Long clientId,
                                  @PathVariable Long carId,
                                  @RequestBody CarServiceRequestDto carServiceRequestDto
    ) {
        return carServiceService.save(clientId, carId, carServiceRequestDto);
    }


    @DeleteMapping("/api/customers/{clientId}/cars/{carId}/car-services/{carServiceId}")
    public ResponseEntity<String> deleteById(@PathVariable Long clientId,
                                             @PathVariable Long carId,
                                             @PathVariable Long carServiceId
    ) {
        return carServiceService.deleteById(clientId, carId, carServiceId);
    }


    @PutMapping("/api/customers/{clientId}/cars/{carId}/car-services/{carServiceId}")
    public CarServiceResponseDto updateById(@PathVariable Long clientId,
                                            @PathVariable Long carId,
                                            @PathVariable Long carServiceId,
                                            @RequestBody CarServiceRequestDto carServiceRequestDto
    ){
        return carServiceService.updateById(clientId, carId, carServiceId, carServiceRequestDto);
    }


    @PutMapping("/api/customers/{clientId}/cars/{carId}/car-services/{carServiceId}/is-paid")
    public ResponseEntity<String> updateIsPaid(@PathVariable Long clientId,
                                               @PathVariable Long carId,
                                               @PathVariable Long carServiceId,
                                               @RequestBody CarServiceIsPaidRequestDto carServiceIsPaidRequestDto
    ){
        return carServiceService.updateIsPaid(clientId, carId, carServiceId, carServiceIsPaidRequestDto);
    }
}
