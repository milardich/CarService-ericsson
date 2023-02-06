package com.ericsson.sm.CarApp.service;

import com.ericsson.sm.CarApp.dto.*;
import org.springframework.http.ResponseEntity;

public interface CarServiceService {
    ClientResponseDto save(Long clientId, Long carId, CarServiceRequestDto carServiceRequestDto);
    ResponseEntity<String> deleteById(Long clientId, Long carId, Long carServiceId);
    CarServiceResponseDto updateById(Long clientId, Long carId, Long carServiceId, CarServiceRequestDto carServiceRequestDto);
    ResponseEntity<String> updateIsPaid(Long clientId, Long carId, Long carServiceId, CarServiceIsPaidRequestDto carServiceIsPaidRequestDto);
}
