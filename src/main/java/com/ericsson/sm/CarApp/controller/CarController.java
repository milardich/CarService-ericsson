package com.ericsson.sm.CarApp.controller;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.CarResponseDto;
import com.ericsson.sm.CarApp.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
