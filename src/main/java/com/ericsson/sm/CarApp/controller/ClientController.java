package com.ericsson.sm.CarApp.controller;

import com.ericsson.sm.CarApp.dto.AllClientsResponseDto;
import com.ericsson.sm.CarApp.dto.ClientRequestDto;
import com.ericsson.sm.CarApp.dto.ClientResponseDto;
import com.ericsson.sm.CarApp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/api/customers")
    public ClientResponseDto save(@RequestBody ClientRequestDto clientRequestDto){
        return clientService.save(clientRequestDto);
    }

    @GetMapping("/api/customers")
    public List<AllClientsResponseDto> getAll(){
        return clientService.getAll();
    }

    @GetMapping("/api/customers/{id}")
    public ClientResponseDto findById(@PathVariable Long id){
        return clientService.findById(id);
    }
}
