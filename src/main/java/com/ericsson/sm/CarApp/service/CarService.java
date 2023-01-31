package com.ericsson.sm.CarApp.service;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.CarResponseDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CarService {
    ClientResponseDto save(Long id, CarRequestDto carRequestDto);
    List<CarResponseDto> getAll();
    CarResponseDto findById(Long id);
    ResponseEntity<String> deleteById(Long clientId, Long carId);
    CarResponseDto updateById(Long clientId, Long carId, CarRequestDto carRequestDto);
}
