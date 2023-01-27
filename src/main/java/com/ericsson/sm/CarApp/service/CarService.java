package com.ericsson.sm.CarApp.service;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.CarResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CarService {
    public CarResponseDto save(CarRequestDto carRequestDto);
    public List<CarRequestDto> getAll();
    public CarRequestDto findById(Long id);
    public ResponseEntity<String> deleteById(Long id);
    public CarRequestDto updateById(Long id);
}
