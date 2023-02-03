package com.ericsson.sm.CarApp.service;

import com.ericsson.sm.CarApp.dto.CarServiceRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;

public interface CarServiceService {
    ClientResponseDto save(Long clientId, Long carId, CarServiceRequestDto carServiceRequestDto);
}
