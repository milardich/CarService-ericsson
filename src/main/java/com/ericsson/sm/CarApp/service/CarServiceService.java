package com.ericsson.sm.CarApp.service;

import com.ericsson.sm.CarApp.dto.CarServiceRequestDto;
import com.ericsson.sm.CarApp.dto.CarServiceResponseDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import org.springframework.http.ResponseEntity;

public interface CarServiceService {
    ClientResponseDto save(Long clientId, Long carId, CarServiceRequestDto carServiceRequestDto);
    ResponseEntity<String> deleteById(Long clientId, Long carId, Long carServiceId);
    CarServiceResponseDto updateById(Long clientId, Long carId, Long carServiceId, CarServiceRequestDto carServiceRequestDto);
}
