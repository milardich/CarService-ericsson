package com.ericsson.sm.CarApp.service;

import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {
    ClientResponseDto save(ClientRequestDto clientRequestDto);
    List<ClientResponseDto> getAll();
    ClientResponseDto findById(Long id);
    ResponseEntity<String> deleteById(Long id);
    ClientResponseDto updateById(Long id, ClientRequestDto clientRequestDto);
}
