package com.ericsson.sm.CarApp.service;

import com.ericsson.sm.CarApp.dto.AllClientsResponseDto;
import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import java.util.List;

public interface ClientService {
    ClientResponseDto save(ClientRequestDto clientRequestDto);
    List<AllClientsResponseDto> getAll();
}
