package com.ericsson.sm.CarApp.service;

import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;

public interface ClientService {
    ClientResponseDto save(ClientRequestDto clientRequestDto);

}
