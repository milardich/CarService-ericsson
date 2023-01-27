package com.ericsson.sm.CarApp.service;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.CarResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CarService {
    CarResponseDto save(CarRequestDto carRequestDto);
    List<CarResponseDto> getAll();
    CarResponseDto findById(Long id);
    ResponseEntity<String> deleteById(Long id);
    CarResponseDto updateById(Long id);
}
