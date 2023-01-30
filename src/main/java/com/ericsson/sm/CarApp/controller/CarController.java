package com.ericsson.sm.CarApp.controller;

import com.ericsson.sm.CarApp.dto.CarResponseDto;
import com.ericsson.sm.CarApp.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/api/cars")
    public List<CarResponseDto> getAll(){
        return carService.getAll();
    }

    @GetMapping("/api/cars/{id}")
    public CarResponseDto getById(@PathVariable Long id){
        return carService.findById(id);
    }
}
