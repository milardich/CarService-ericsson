package com.ericsson.sm.CarApp.service;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.CarResponseDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;

import java.util.List;

public interface CarService {
    ClientResponseDto save(Long id, CarRequestDto carRequestDto);
    List<CarResponseDto> getAll();
    CarResponseDto findById(Long id);
}
