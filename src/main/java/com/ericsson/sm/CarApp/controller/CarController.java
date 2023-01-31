package com.ericsson.sm.CarApp.controller;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.CarResponseDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @PostMapping("/api/customers/{id}/cars")
    public ClientResponseDto save(@PathVariable Long id, @RequestBody CarRequestDto carRequestDto){
        return carService.save(id, carRequestDto);
    }

    @DeleteMapping("/api/customers/{clientId}/cars/{carId}")
    public ResponseEntity<String> deleteById(@PathVariable Long clientId, @PathVariable Long carId){
        return carService.deleteById(clientId, carId);
    }

    @GetMapping("/api/cars")
    public List<CarResponseDto> getAll(){
        return carService.getAll();
    }

    @GetMapping("/api/cars/{id}")
    public CarResponseDto getById(@PathVariable Long id){
        return carService.findById(id);
    }
}
