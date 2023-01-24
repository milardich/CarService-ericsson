package com.ericsson.sm.CarApp.service;

import com.ericsson.sm.CarApp.dto.AllClientsResponseDto;
import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.model.Client;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    ClientResponseDto save(ClientRequestDto clientRequestDto);
    List<AllClientsResponseDto> getAll();
    ClientResponseDto findById(Long id);
    ResponseEntity<String> deleteById(Long id);
    ClientResponseDto updateById(Long id, ClientRequestDto clientRequestDto);
}
