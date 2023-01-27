package com.ericsson.sm.CarApp.controller;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.CarResponseDto;
import com.ericsson.sm.CarApp.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @PostMapping("/api/cars")
    public CarResponseDto save(@RequestBody CarRequestDto carRequestDto){
        return carService.save(carRequestDto);
    }

    @GetMapping("/api/cars")
    public List<CarResponseDto> getAll(){
        return carService.getAll();
    }

    @GetMapping("/api/cars/{id}")
    public CarResponseDto getById(@PathVariable Long id){
        return carService.findById(id);
    }

    @DeleteMapping("/api/cars/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return carService.deleteById(id);
    }
}
