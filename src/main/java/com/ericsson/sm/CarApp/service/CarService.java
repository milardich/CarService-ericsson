package com.ericsson.sm.CarApp.service;

import com.ericsson.sm.CarApp.dto.CarRequestDto;
import com.ericsson.sm.CarApp.dto.CarResponseDto;

import java.util.List;

public interface CarService {
    List<CarResponseDto> getAll();
    CarResponseDto findById(Long id);
}
